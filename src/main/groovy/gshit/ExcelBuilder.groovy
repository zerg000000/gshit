package gshit

import gshit.factory.WorkbookFactory
import gshit.factory.SheetFactory
import gshit.factory.CellFactory
import gshit.factory.RowFactory
import gshit.factory.FontFactory
import gshit.factory.CellStyleFactory
import gshit.factory.CSSFactory
import gshit.factory.MarkerFactory
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.CellStyle
import gshit.factory.DataFormatFactory

class ExcelBuilder extends FactoryBuilderSupport {

    private Map<String,Font> fonts = [:]
    private Map<String,CellStyle> styles = [:]
    private Map<String,Integer> formats = [:]
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
        registerFactory('format', new DataFormatFactory(builder: this))
        registerFactory('css', new CSSFactory(builder:  this))
        registerFactory('marker', new MarkerFactory(builder: this))
    }
}
