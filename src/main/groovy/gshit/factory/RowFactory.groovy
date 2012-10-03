package gshit.factory

import gshit.ExcelBuilder

class RowFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = false

    int rowCount = 0

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        return builder.current.createRow( (value instanceof Integer)? value : rowCount++)
    }
}
