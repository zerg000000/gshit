package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.ss.usermodel.Font

class FontFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        if(!builder.context['font'][value])
            builder.context['font'][value] = builder.context['workbook'].createFont()
        Font f = builder.context['font'][value]
        attributes.each { k, v -> if(k in Font.metaClass.properties*.name) f.'$k' = v}
        f
    }
}
