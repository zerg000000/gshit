package gshit.factory

import org.apache.poi.ss.usermodel.DataFormat
import gshit.ExcelBuilder

class DataFormatFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    boolean processNodeChildren = false

    private DataFormat dataFormat

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        dataFormat = !(dataFormat)?builder.current.createDataFormat():dataFormat
        builder.formats[value[0]] = dataFormat.getFormat(value[1] as String)
    }
}
