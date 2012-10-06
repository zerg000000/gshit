import spock.lang.*
import gshit.ExcelBuilder

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
            font(name:'normal',bold:true,fontHeightInPoints:25)
            style(name:'default',font:'normal')
            sheet('abc') {
                row { cell('al','ac') }

                row { cell('al','ddddd','kdjfke') }
            }

            css(sheet:'abc',row:0..1,col:[0..1,2],style:'default')
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
            font(name:'normal',bold:true,fontHeightInPoints:256,underline:'double')
            style(name:'default',font:'normal')
            sheet('abc') {
                row { cell('al','ac') }
                marker('group1') {
                    row { cell('al','ddddd','kdjfke') }
                }
            }

            css(marker:'group1',sheet:'abc',row:0,col:[0,1,2],style:'default')
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
                row { cell(['generated Date', new Date()]) }
                ['2012', '2011', '2010'].each { title ->
                    marker('group1') {
                        row{ cell( title ) }
                        row { cell('Item', 'Total', 'Producing from') }

                        ['Peter Pan', 'Mary Kay', 'Bob Bill']
                        .each { subtitle ->
                            marker('group2') {
                                row { cell( subtitle ) }
                                row { cell( 'increased salary', 300 * subtitle.size(), 'abc' ) }
                            }
                        }

                        row{cell('end title')}
                    }
                }
            }
            format('normal-date', 'yyyy-MMM-dd')
            font(name:'normal',bold:true,fontHeightInPoints:25,underline:'double')
            font(name:'title',bold:true,italic:true,fontHeightInPoints:32,underline:'single')
            font(name:'subtitle', bold: true, fontHeightInPoints: 28)
            style(name: 'normal-date', font: 'normal', format: 'normal-date')
            style(name:'default',font:'normal')
            style(name:'title', font:'title')
            style(name: 'subtitle', font: 'subtitle')

            css(sheet: 'Statement Report', row: 0,col: 1,style:'normal-date')
            css(marker:'group1',sheet:'Statement Report',row:0,col:0,style:'title')
            css(marker:'group1',sheet:'Statement Report',row:1,col:0..2,style:'default')
            css(marker:'group2',sheet:'Statement Report',row:0,col:0,style:'subtitle')
        }.write(new FileOutputStream("test.xlsx"))

        expect:
        new File("test.xlsx").exists()

        //cleanup:
        //new File("test.xlsx").delete()
    }
}
