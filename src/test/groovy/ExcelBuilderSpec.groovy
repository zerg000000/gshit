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
            font {

            }
            style {

            }
        }
    }

}
