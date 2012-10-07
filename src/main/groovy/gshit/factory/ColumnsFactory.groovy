package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.ss.usermodel.Sheet

class ColumnsFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    boolean processNodeChildren = true

    Closure adjustColumn = {}

    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        false
    }

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        FactoryBuilderSupport.checkValueIsType(value, name, List)
        Sheet sht = builder.current as Sheet
        setColumn = {
            value.eachWithIndex { e, i ->
                if (e instanceof Number) {
                    sht.setColumnWidth(i, e.intValue())
                } else if ('auto') {
                    sht.autoSizeColumn(i.intValue())
                }
            }
        }
        null
    }
}
