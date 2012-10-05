package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.ss.usermodel.CellStyle

class CellStyleFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    boolean processNodeChildren = true

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        CellStyle s = builder.current.createCellStyle()
        builder.styles[attributes['name']] = s
        attributes.each { k, v ->
            if(k in CellStyle.metaClass.properties*.name) {
                if(k == 'font') {
                  s.font = builder.fonts[v]
                }
            }
        }
    }
}
