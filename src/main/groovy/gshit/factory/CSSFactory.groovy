package gshit.factory

import gshit.ExcelBuilder

class CSSFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {

        builder.current.getSheet(attributes['sheet']).getRow(attributes['row']).getCell(attributes['col']).setCellStyle(builder.styles[attributes['style']])
    }
}
