package gshit.factory

import gshit.ExcelBuilder

class MarkerFactory extends AbstractFactory {
    ExcelBuilder builder

    boolean leaf = false

    boolean handlesNodeChildren = false

    Map<String,List<Integer>> markers = [:]

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
        if(!markers[value])markers[value] = []
        markers[value].add(builder.factories['row'].rowCount)
        return builder.current
    }
}
