package gshit.setter

import gshit.setter.PropertySetter

class SimplePropertySetter implements PropertySetter {
    private String name
    private Class classType

    public void set(Object obj, Object value) {
        if(value == null || classType.isInstance(value)) {
            obj."$name" = value
        }
    }
}
