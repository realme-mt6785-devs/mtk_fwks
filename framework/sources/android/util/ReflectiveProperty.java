package android.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes3.dex */
class ReflectiveProperty<T, V> extends Property<T, V> {
    private static final String PREFIX_GET = "get";
    private static final String PREFIX_IS = "is";
    private static final String PREFIX_SET = "set";
    private Field mField;
    private Method mGetter;
    private Method mSetter;

    public ReflectiveProperty(Class<T> propertyHolder, Class<V> valueType, String name) {
        super(valueType, name);
        char firstLetter = Character.toUpperCase(name.charAt(0));
        String theRest = name.substring(1);
        String capitalizedName = firstLetter + theRest;
        String getterName = PREFIX_GET + capitalizedName;
        try {
            this.mGetter = propertyHolder.getMethod(getterName, null);
        } catch (NoSuchMethodException e) {
            String getterName2 = PREFIX_IS + capitalizedName;
            try {
                this.mGetter = propertyHolder.getMethod(getterName2, null);
            } catch (NoSuchMethodException e2) {
                try {
                    Field field = propertyHolder.getField(name);
                    this.mField = field;
                    Class fieldType = field.getType();
                    if (!typesMatch(valueType, fieldType)) {
                        throw new NoSuchPropertyException("Underlying type (" + fieldType + ") does not match Property type (" + valueType + ")");
                    }
                    return;
                } catch (NoSuchFieldException e3) {
                    throw new NoSuchPropertyException("No accessor method or field found for property with name " + name);
                }
            }
        }
        Class getterType = this.mGetter.getReturnType();
        if (typesMatch(valueType, getterType)) {
            String setterName = PREFIX_SET + capitalizedName;
            try {
                this.mSetter = propertyHolder.getMethod(setterName, getterType);
            } catch (NoSuchMethodException e4) {
            }
        } else {
            throw new NoSuchPropertyException("Underlying type (" + getterType + ") does not match Property type (" + valueType + ")");
        }
    }

    private boolean typesMatch(Class<V> valueType, Class getterType) {
        if (getterType == valueType) {
            return true;
        }
        if (!getterType.isPrimitive()) {
            return false;
        }
        if (getterType == Float.TYPE && valueType == Float.class) {
            return true;
        }
        if (getterType == Integer.TYPE && valueType == Integer.class) {
            return true;
        }
        if (getterType == Boolean.TYPE && valueType == Boolean.class) {
            return true;
        }
        if (getterType == Long.TYPE && valueType == Long.class) {
            return true;
        }
        if (getterType == Double.TYPE && valueType == Double.class) {
            return true;
        }
        if (getterType == Short.TYPE && valueType == Short.class) {
            return true;
        }
        if (getterType == Byte.TYPE && valueType == Byte.class) {
            return true;
        }
        return getterType == Character.TYPE && valueType == Character.class;
    }

    @Override // android.util.Property
    public void set(T object, V value) {
        Method method = this.mSetter;
        if (method != null) {
            try {
                method.invoke(object, value);
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        } else {
            Field field = this.mField;
            if (field != null) {
                try {
                    field.set(object, value);
                } catch (IllegalAccessException e3) {
                    throw new AssertionError();
                }
            } else {
                throw new UnsupportedOperationException("Property " + getName() + " is read-only");
            }
        }
    }

    @Override // android.util.Property
    public V get(T object) {
        Method method = this.mGetter;
        if (method != null) {
            try {
                return (V) method.invoke(object, null);
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        } else {
            Field field = this.mField;
            if (field != null) {
                try {
                    return (V) field.get(object);
                } catch (IllegalAccessException e3) {
                    throw new AssertionError();
                }
            } else {
                throw new AssertionError();
            }
        }
    }

    @Override // android.util.Property
    public boolean isReadOnly() {
        return this.mSetter == null && this.mField == null;
    }
}
