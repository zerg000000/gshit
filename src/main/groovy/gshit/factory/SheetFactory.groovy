package gshit.factory

import gshit.ExcelBuilder
import gshit.setter.PropertySetter
import gshit.setter.Setters
import org.apache.poi.ss.util.WorkbookUtil

class SheetFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = false

    Map<String, PropertySetter> setters = [:]

    @Override
    void onFactoryRegistration(FactoryBuilderSupport builder, String registeredName, String group) {
        setters = [
                //arrayFormula
                'autobreaks': Setters.setter('autobreaks', Boolean),
                //autoFilter
                'columnBreak': Setters.setter('columnBreak', Integer),
                //'columnGroupCollapsed'
                //'columnHidden'
                //
        ]
        super.onFactoryRegistration(builder, registeredName, group)    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {

        false
    }

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        builder.factories['row'].resetRowCount()
        return builder.current.createSheet(WorkbookUtil.createSafeSheetName(value as String))
    }
}
