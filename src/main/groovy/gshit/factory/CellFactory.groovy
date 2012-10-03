package gshit.factory

import gshit.ExcelBuilder

class CellFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        return value.eachWithIndex { e, i -> builder.current.createCell(i).setCellValue(e)}
    }
}
