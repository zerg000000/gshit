import gshit.setter.Setters
import spock.lang.Specification

class SettersSpec extends Specification {
    static final enum TestEnum {
        TEST1, TEST2, TEST3, TEST_4;

        public TestEnum() {}

        public getValue() {this}
    }

    def "Enum Setter should accept lowercase and -"() {
        setup:
        def setter = Setters.enumsetter('value', TestEnum.TEST1)
        def map = ['value': null]
        expect:
        setter.set(map, sweetword)
        map['value'] == testenum
        where:
        sweetword | testenum
        'test1'   | TestEnum.TEST1
        'teSt2'   | TestEnum.TEST2
        'TEST3'   | TestEnum.TEST3
        'test-4'  | TestEnum.TEST_4
    }

    def "Simple Setter should simple apply values without check"() {
        setup:
        def setter = Setters.setter('value', String)
        def map = ['value': 'dd']
        expect:
        setter.set(map, sweetword)
        map['value'] == value
        where:
        sweetword | value
        'abc'     | 'abc'
        'abcc'    | 'abcc'
        null      | null
    }
}
