package gshit.factory

import gshit.ExcelBuilder
import org.apache.poi.ss.usermodel.Cell

class CSSFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = true

    boolean handlesNodeChildren = false

    @Override
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        select(attributes['sheet'],attributes['row'],attributes['col'])
                {cell -> cell?.setCellStyle(builder.styles[attributes['style']])}
        null
    }

    public void select(Object sheet, Object row, Object col, Closure closure) {
        def sheets = []
        if(FactoryBuilderSupport.checkValueIsType(sheet,'sheet',String)) {
            sheets.add(sheet)
        } else {
            sheets.addAll(sheet)
        }

        def rows = getNumList(row)
        def cols = getNumList(col)

        sheets.each { sht ->
            rows.each {
                rowNo ->
                cols.each {
                    colNo ->
                    closure.call(builder.current?.getSheet(sht)?.getRow(rowNo)?.getCell(colNo))
                }
            }
        }
    }

    private List<Integer> getNumList(row) {
        def rows = []
        if (row instanceof Integer) {
            rows.add(row)
        } else if (row instanceof Range) {
            rows.addAll(row)
        } else if (row instanceof List) {
            row.each { r ->
                rows.addAll(getNumList(r))
            }
        }
        rows
    }
}
