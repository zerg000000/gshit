package gshit.factory

import gshit.ExcelBuilder
import gshit.setter.PropertySetter
import gshit.setter.Setters
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.usermodel.VerticalAlignment

class CellStyleFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = true

    boolean processNodeChildren = true

    Map<String, PropertySetter> setters = [:]

    @Override
    public void onFactoryRegistration(FactoryBuilderSupport builder, String registeredName, String group) {
        setters = [
                'font': Setters.msetter('font', builder.fonts),
                'format': Setters.msetter('dataFormat', builder.formats),
                'border-top': Setters.enumsetter('borderTop', BorderStyle.DASH_DOT),
                'border-bottom': Setters.enumsetter('borderBottom', BorderStyle.DASH_DOT),
                'border-left': Setters.enumsetter('borderLeft', BorderStyle.DASH_DOT),
                'border-right': Setters.enumsetter('borderRight', BorderStyle.DASH_DOT),
                'alignment': Setters.enumsetter('alignment', HorizontalAlignment.CENTER),
                'v-align': Setters.enumsetter('verticalAlignment', VerticalAlignment.CENTER),
                'hidden': Setters.setter('hidden', Boolean),
                'indent': Setters.setter('indent', Integer),
                'locked': Setters.setter('locked', Boolean),
                'rotation': Setters.setter('rotation', Integer),
                'wrap-text': Setters.setter('wrapText', Boolean)
                //TODO color relatived

        ]
        super.onFactoryRegistration(builder, registeredName, group)
    }

    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        attributes.each { k, v -> setters[k]?.set(node, v) }
        false
    }

    @Override
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        CellStyle s = builder.current.createCellStyle()
        builder.styles[attributes['name']] = s

    }
}
