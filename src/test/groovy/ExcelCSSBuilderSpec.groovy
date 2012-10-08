import spock.lang.*
import gshit.setter.ExcelCSSBuilder

class ExcelCSSBuilderSpec extends Specification {
    def 'simple nested'() {
       setup:
        ExcelCSSBuilder b = new ExcelCSSBuilder()
        def r = b.css {
            marker('1') {
                style('2') {
                    sheet('3') {
                        cell(row: 1, col: 2)
                    }
                }
            }
        }
       expect:
        r[0]['row'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['row']
        r[0]['col'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['col']
        r[0]['marker'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['marker']
        r[0]['style'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['style']
        r[0]['sheet'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['sheet']

    }

    def 'should allow delegate to closure'() {
        setup:
        ExcelCSSBuilder b = new ExcelCSSBuilder()
        def c = {
        css {
            marker('1') {
                style('2') {
                    sheet('3') {
                        cell(row: 1, col: 2)
                    }
                }
            }
        }
        }
        c.setDelegate(b)
        def r = c.call()

        expect:
        r[0]['row'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['row']
        r[0]['col'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['col']
        r[0]['marker'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['marker']
        r[0]['style'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['style']
        r[0]['sheet'] == [row:1, col:2, marker:'1', style:'2', sheet:'3']['sheet']

    }
}
