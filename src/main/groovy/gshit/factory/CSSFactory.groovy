package gshit.factory

import gshit.ExcelBuilder
import gshit.setter.ExcelCSSBuilder

class CSSFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = true

    boolean processNodeChildren = false

    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        false
    }

    @Override
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        builder.current
    }

    @Override
    boolean onNodeChildren(FactoryBuilderSupport builder, Object node, Closure childContent) {
        ExcelCSSBuilder cssBuilder = new ExcelCSSBuilder()

        List<Map<String,Object>> selectors = builder.withBuilder(cssBuilder,{css(childContent)})
        selectors.each { s ->
            select(s['sheet'], s['row'], s['col'], s['marker'])
                    {cell -> cell?.setCellStyle(builder.styles[s['style']])}
        }

        false
    }

    public void select(Object sheet, Object row, Object col, Object marker, Closure closure) {
        List<String> sheets = []
        if (FactoryBuilderSupport.checkValueIsType(sheet, 'sheet', String)) {
            sheets.add(sheet)
        } else {
            sheets.addAll(sheet)
        }

        List<Integer> rows = expandNumList(row)
        List<Integer> cols = expandNumList(col)
        List<Integer> markers = (marker) ? builder.factories['marker'].markers[marker] : [0]

        sheets.each { sht ->
            markers.each {
                markerNo ->

                rows.each {
                    rowNo ->
                    cols.each {
                        colNo ->
                        closure.call(builder.current?.getSheet(sht)?.getRow(markerNo + rowNo)?.getCell(colNo))
                    }
                }
            }
        }
    }

    private List<Integer> expandNumList(row) {
        def rows = []
        if (row instanceof Integer) {
            rows.add(row)
        } else if (row instanceof Range) {
            rows.addAll(row)
        } else if (row instanceof List) {
            row.each { r ->
                rows.addAll(expandNumList(r))
            }
        }
        rows
    }
}
