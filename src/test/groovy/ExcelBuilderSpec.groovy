import gshit.ExcelBuilder
import spock.lang.Specification

class ExcelBuilderSpec extends Specification {

    def "builder create an empty workbook"() {
        setup:
        new ExcelBuilder().workbook {}.write(new FileOutputStream("test.xlsx"))

        expect:
        new File("test.xlsx").exists()

        cleanup:
        new File("test.xlsx").delete()
    }

    def "builder create a simple workbook"() {
        setup:
        new ExcelBuilder()
                .workbook {
            sheet("abc") {
                columns('auto', 2 * 256, 'auto', 'auto')
                row {
                    cell('as', 'as', '', 'as', '')
                }
            }
        }.write(new FileOutputStream("test.xlsx"))

        expect:
        new File("test.xlsx").exists()

        cleanup:
        new File("test.xlsx").delete()
    }

    def "should allow looping sheets"() {
        setup:
        new ExcelBuilder()
                .workbook {
            ["abc", "efd", "efg"].each { t -> sheet(t) {} }
        }.write(new FileOutputStream("test.xlsx"))

        expect:
        new File("test.xlsx").exists()

        cleanup:
        new File("test.xlsx").delete()
    }

    def "create style and font in workbook"() {
        setup:
        new ExcelBuilder()
                .workbook {
            font(id: 'normal', bold: true, fontHeightInPoints: 25)
            style(id: 'default', font: 'normal')
            sheet('abc') {
                row { cell('al', 'ac') }

                row { cell('al', 'ddddd', 'kdjfke') }
            }

            css{
                cell(sheet: 'abc', row: 0..1, col: [0..1, 2], style: 'default')
            }
        }.write(new FileOutputStream("test.xlsx"))

        expect:
        new File("test.xlsx").exists()

        cleanup:
        new File("test.xlsx").delete()
    }

    def "should position using marker"() {
        setup:
        new ExcelBuilder()
                .workbook {
            font(id: 'normal', bold: true, fontHeightInPoints: 256, underline: 'double')
            style(id: 'default', font: 'normal')
            sheet('abc') {
                row { cell('al', 'ac') }
                marker('group1') {
                    row { cell('al', 'ddddd', 'kdjfke') }
                }
            }

            css{
                cell(marker: 'group1', sheet: 'abc', row: 0, col: [0, 1, 2], style: 'default')
            }
        }.write(new FileOutputStream("test.xlsx"))

        expect:
        new File("test.xlsx").exists()

        cleanup:
        new File("test.xlsx").delete()
    }

    def "marker should work in nested loop"() {
        setup:
        new ExcelBuilder()
                .workbook {

            sheet('Statement Report') {
                columns('auto', 'auto', 'auto', 'auto')
                row { cell(['generated Date', new Date()]) }
                ['2012', '2011', '2010'].each { title ->
                    marker('group1') {
                        row { cell(title) }
                        row { cell('Item', 'Total', 'Producing from') }

                        ['Peter Pan', 'Mary Kay', 'Bob Bill']
                                .each { subtitle ->
                            marker('group2') {
                                row { cell(subtitle) }
                                row { cell('increased salary', 300 * subtitle.size(), 'abc') }
                            }
                        }

                        row {cell('end title')}
                    }
                }
            }
            format('normal-date', 'yyyy-MMM-dd')
            font(id: 'normal', bold: true, fontHeightInPoints: 25, underline: 'double')
            font(id: 'title', bold: true, italic: true, fontHeightInPoints: 32, underline: 'single')
            font(id: 'subtitle', bold: true, fontHeightInPoints: 28)
            style(id: 'normal-date', font: 'normal', format: 'normal-date')
            style(id: 'default', font: 'normal')
            style(id: 'title', font: 'title')
            style(id: 'subtitle', font: 'subtitle')

            css {
                sheet('Statement Report'){
                  cell(style: 'normal-date', row: 0, col: 1)
                  marker('group1') {
                      cell(style: 'title', row: 0, col: 0)
                      cell(style: 'default', row: 1, col: 0..2)
                  }
                  marker('group2') {
                      cell(style: 'subtitle', row: 0, col: 0)
                  }
                }
            }
        }.write(new FileOutputStream("test.xlsx"))

        expect:
        new File("test.xlsx").exists()

        cleanup:
        new File("test.xlsx").delete()
    }

    def "should allow script"() {
        setup:
        String text = '''
        workbook {
            font(id: 'normal', bold: true, fontHeightInPoints: 25)
            style(id: 'default', font: 'normal')

            sheet('abc') {
               dumbs.each { d ->
                row { cell(d) }
               }
            }

            css{
              cell(sheet: 'abc', row: 0..1, col: [0..1, 2], style: 'default')
            }
        }
        '''
        GroovyCodeSource c = new GroovyCodeSource(text,'test.script','test.script')
        Class compiledScript =  new GroovyClassLoader(Thread.currentThread().getContextClassLoader()).parseClass(c)

        def excel = new ExcelBuilder()
        excel.setVariable('dumbs',['abc','efg','rgt','wer'])
        excel
        .build(compiledScript)
        .write(new FileOutputStream("test.xlsx"))

        expect:
        new File("test.xlsx").exists()

        cleanup:
        new File("test.xlsx").delete()
    }


}
