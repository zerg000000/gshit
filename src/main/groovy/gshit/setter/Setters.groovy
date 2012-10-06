package gshit.setter

import gshit.setter.PropertySetter
import gshit.setter.SimplePropertySetter
import gshit.setter.EnumPropertySetter
import gshit.setter.MapPropertySetter

class Setters {
    public static PropertySetter setter(String name, Class classType) {
        new SimplePropertySetter(name:name , classType:classType)
    }
    public static PropertySetter msetter(String name, Map values) {
        new MapPropertySetter(name: name, vals: values)
    }
    public static PropertySetter enumsetter(String name, Enum<?> type) {
        new EnumPropertySetter(name: name, type: type)
    }
}
