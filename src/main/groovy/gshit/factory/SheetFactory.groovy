package gshit.factory

import gshit.ExcelBuilder

class SheetFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = false

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        return builder.current.createSheet(value)
    }
}
