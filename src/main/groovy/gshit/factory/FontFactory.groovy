package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.ss.usermodel.Font

class FontFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        Font f = builder.current.createFont()
        builder.fonts[attributes['name']] = f
        attributes.each { k, v ->
            if(['fontName','color','bold','fontHeightInPoints'].contains(k))
              {f."$k" = v}
        }
    }
}
