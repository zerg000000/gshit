package gshit

import gshit.factory.WorkbookFactory
import gshit.factory.SheetFactory
import gshit.factory.CellFactory
import gshit.factory.RowFactory
import gshit.factory.FontFactory
import gshit.factory.CellStyleFactory
import gshit.factory.CSSFactory
import gshit.factory.MarkerFactory

class ExcelBuilder extends FactoryBuilderSupport {

    private Map<String,Object> fonts = [:]
    private Map<String,Object> styles = [:]
    private Map<String,List<Integer>> markers = [:]

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
        registerFactory('style', new CellStyleFactory(builder:  this))
        registerFactory('css', new CSSFactory(builder:  this))
        registerFactory('marker', new MarkerFactory(builder: this))
    }
}
