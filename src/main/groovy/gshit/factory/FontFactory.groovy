package gshit.factory

import gshit.ExcelBuilder
import gshit.setter.PropertySetter
import gshit.setter.Setters
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.FontCharset
import org.apache.poi.ss.usermodel.FontUnderline

class FontFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    Map<String, PropertySetter> setters = [
            fontName: Setters.setter('fontName', String),
            bold: Setters.setter('bold', Boolean),
            fontHeight: Setters.setter('fontHeight', Integer),
            fontHeightInPoints: Setters.setter('fontHeightInPoints', Integer),
            italic: Setters.setter('italic', Boolean),
            strikeout: Setters.setter('strikeout', Boolean),
            boldweight: Setters.msetter('boldweight',
                    ['normal': Font.BOLDWEIGHT_NORMAL,
                            'bold': Font.BOLDWEIGHT_BOLD]),
            underline: Setters.enumsetter('underline', FontUnderline.NONE),
            typeOffset: Setters.msetter('typeOffset',
                    ['none': Font.SS_NONE,
                            'super': Font.SS_SUPER,
                            'sub': Font.SS_SUB]),
            charset: Setters.enumsetter('charset', FontCharset.ANSI)
    ]

    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        attributes.each { k, v -> setters[k]?.set(node, v) }
        false
    }

    @Override
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        Font f = builder.current.createFont()
        builder.fonts[attributes['name']] = f
    }
}
