import spock.lang.*
import gshit.ExcelBuilder

class ExcelBuilderSpec extends Specification {

    def "builder create an empty workbook"() {
        setup:
        new ExcelBuilder().workbook {}.write(new FileOutputStream("test.xls"))

        expect:
        new File("test.xls").exists()

        cleanup:
        new File("test.xls").delete()
    }

    def "builder create a simple workbook"() {
        setup:
        new ExcelBuilder()
                .workbook {
            sheet("abc") {
                row {
                    cell(['as', 'as', '', 'as', ''])
                }
            }
        }.write(new FileOutputStream("test.xls"))

        expect:
        new File("test.xls").exists()

        cleanup:
        new File("test.xls").delete()
    }

    def "should allow looping sheets"() {
        setup:
        new ExcelBuilder()
                .workbook {
            ["abc", "efd", "efg"].each { t -> sheet(t) {} }
        }.write(new FileOutputStream("test.xls"))

        expect:
        new File("test.xls").exists()

        cleanup:
        new File("test.xls").delete()
    }

    def "create style and font in workbook"() {
        setup:
        new ExcelBuilder()
                .workbook {
            font(name:'normal',bold:true,fontHeightInPoints:25)
            style(name:'default',font:'normal')
            sheet('abc') {
                row { cell(['al','ac']) }

                row { cell(['al','ddddd','kdjfke']) }
            }

            css(sheet:'abc',row:0..1,col:[0..1,2],style:'default')
        }.write(new FileOutputStream("test.xls"))

        expect:
        new File("test.xls").exists()

        cleanup:
        new File("test.xls").delete()
    }

    def "should position using marker"() {
        setup:
        new ExcelBuilder()
                .workbook {
            font(name:'normal',bold:true,fontHeightInPoints:256,underline:'double')
            style(name:'default',font:'normal')
            sheet('abc') {
                row { cell(['al','ac']) }
                //marker("group1") {
                    row { cell(['al','ddddd','kdjfke']) }
                //}
            }

            css(maker:'group1',sheet:'abc',row:0,col:[0,1,2],style:'default')
        }.write(new FileOutputStream("test.xls"))

        expect:
        new File("test.xls").exists()

        //cleanup:
        //new File("test.xls").delete()
    }
}
