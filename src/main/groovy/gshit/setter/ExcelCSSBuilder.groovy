package gshit.setter

class ExcelCSSBuilder extends FactoryBuilderSupport {

    ExcelCSSBuilder() {
        super()
        autoRegisterNodes()
    }

    public void registerMakeCell() {
        DummyMapFactory f = new DummyMapFactory()
        DummyListFactory l = new DummyListFactory()

        registerFactory('css', l)
        registerFactory('marker', f)
        registerFactory('sheet', f)
        registerFactory('style', f)
        registerFactory('row', f)
        registerFactory('col', f)
        registerFactory('cell', new EndFactory(done: {b,p,n -> l.list.add(n)}))
    }

    class DummyListFactory extends AbstractFactory {
        List list = []

        @Override
        Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
            list
        }
    }

    class DummyMapFactory extends AbstractFactory {

        @Override
        Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
            Map vars = (builder.current instanceof Map) ? builder.current : [:]
            vars[name] = value? value : attributes
            vars
        }
    }

    class EndFactory extends AbstractFactory {
        Closure done = {}

        boolean leaf = true

        boolean handlesNodeChildren = true

        @Override
        Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) {
            if(value) { attributes[name] = value }
            attributes
        }

        @Override
        void onNodeCompleted(FactoryBuilderSupport builder, Object parent, Object node) {
            node.putAll(parent)
            done.call(builder,parent,node)
        }
    }
}
