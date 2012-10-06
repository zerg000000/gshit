package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.ss.util.WorkbookUtil

class SheetFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = false

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        builder.factories['row'].resetRowCount()
        return builder.current.createSheet(WorkbookUtil.createSafeSheetName(value as String))
    }
}
