package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.ss.usermodel.CellStyle
import gshit.setter.PropertySetter
import gshit.setter.Setters

class CellStyleFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    boolean processNodeChildren = true

    Map<String, PropertySetter> setters = [:]

    @Override
    public void onFactoryRegistration(FactoryBuilderSupport builder, String registeredName, String group) {
        setters = [
                font : Setters.msetter('font',builder.fonts)
        ]
        super.onFactoryRegistration(builder, registeredName, group)
    }

    @Override
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        CellStyle s = builder.current.createCellStyle()
        builder.styles[attributes['name']] = s
        attributes.each { k, v ->
            setters[k]?.set(s,v)
        }
    }
}
