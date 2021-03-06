package gshit

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font
import gshit.factory.*

class ExcelBuilder extends FactoryBuilderSupport {

    private Map<String, Font> fonts = [:]
    private Map<String, CellStyle> styles = [:]
    private Map<String, Integer> formats = [:]
    private Map<String, List<Integer>> markers = [:]

    ExcelBuilder() {
        autoRegisterNodes()
    }

    public void registerBasicComponent() {
        registerFactory('workbook', new WorkbookFactory(builder: this))
        registerFactory('sheet', new SheetFactory(builder: this))
        registerFactory('row', new RowFactory(builder: this))
        registerFactory('cell', new CellFactory(builder: this))
    }

    public void registerStylingComponent() {
        registerFactory('font', new FontFactory(builder: this))
        registerFactory('style', new CellStyleFactory(builder: this))
        registerFactory('format', new DataFormatFactory(builder: this))
    }

    public void registerStylingCommand() {
        registerFactory('css', new CSSFactory(builder: this))
        registerFactory('marker', new MarkerFactory(builder: this))
    }

    public void registerSizing() {
        registerFactory('columns', new ColumnsFactory(builder: this))
    }
}
