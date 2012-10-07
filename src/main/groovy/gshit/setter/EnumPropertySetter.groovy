package gshit.setter

class EnumPropertySetter implements PropertySetter {

    private String name
    private Enum<?> type

    @Override
    void set(Object obj, Object val) {
        if (val != null) {
            obj[name] = type.valueOf(val.toUpperCase().replace('-', '_')).getValue()
        }
    }
}
