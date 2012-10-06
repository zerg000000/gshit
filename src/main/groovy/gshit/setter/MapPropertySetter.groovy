package gshit.setter

class MapPropertySetter implements PropertySetter {
    private String name
    private Map vals

    @Override
    void set(Object obj, Object val) {
        if (vals.containsKey(val)) {
            obj."$name" = vals[val]
        }
    }
}
