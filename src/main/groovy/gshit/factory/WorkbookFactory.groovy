package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class WorkbookFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = false

    boolean processNodeChildren = true

    @Override
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        (value == 'xls') ? new HSSFWorkbook() : new XSSFWorkbook()
    }

    @Override
    void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
        builder.factories['columns'].adjustColumn()
    }
}
