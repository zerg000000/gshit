package gshit

import gshit.factory.WorkbookFactory
import gshit.factory.SheetFactory
import gshit.factory.CellFactory
import gshit.factory.RowFactory
import gshit.factory.FontFactory

class ExcelBuilder extends FactoryBuilderSupport {

    ExcelBuilder() {
        autoRegisterNodes()
    }

    public void  registerBasicComponent() {
        registerFactory('workbook', new WorkbookFactory(builder:this))
        registerFactory('sheet', new SheetFactory(builder:this))
        registerFactory('row', new RowFactory(builder:this))
        registerFactory('cell', new CellFactory(builder: this))
    }

    public void registerStyling() {
        registerFactory('font', new FontFactory(builder: this))
    }
}
