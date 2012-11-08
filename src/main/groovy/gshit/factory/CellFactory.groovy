package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell

class CellFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        if (!(value instanceof List)) {
            value = [value]
        }
        Row row = builder.current
        value.eachWithIndex { e, i ->
            Cell cell = row.createCell(i)
            if(e instanceof String && e.startsWith('=')) {
                cell.setCellFormula(e)
            }
            cell.setCellValue(e)
        }
    }
}
