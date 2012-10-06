package gshit.factory

import gshit.ExcelBuilder

class RowFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = false

    int rowCount = 0

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        rowCount = (value)?((Integer)value):rowCount
        def row = builder.current.createRow(rowCount)
        rowCount = rowCount + 1
        row
    }
}
