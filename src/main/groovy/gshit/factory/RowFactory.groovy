package gshit.factory

import gshit.ExcelBuilder
import gshit.setter.PropertySetter
import gshit.setter.Setters

class RowFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = false

    Map<String, PropertySetter> setters = [:]

    @Override
    void onFactoryRegistration(FactoryBuilderSupport builder, String registeredName, String group) {
        setters = [
                'height': Setters.setter('height', Integer),
                'heightInPoints': Setters.setter('heightInPoints', Double),
                'zeroHeight': Setters.setter('zeroHeight', Boolean)
        ]
        super.onFactoryRegistration(builder, registeredName, group)    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        attributes.each { k, v -> setters[k]?.set(node, v) }
        false
    }

    int rowCount = 0

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        rowCount = (value) ? ((Integer) value) : rowCount
        def row = builder.current.createRow(rowCount)
        rowCount = rowCount + 1
        row
    }

    public void resetRowCount() {
        rowCount = 0
    }
}
