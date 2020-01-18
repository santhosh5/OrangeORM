package santhosh.pm.ormorange.inflater.field;

import android.database.Cursor;

import java.lang.reflect.Field;

import santhosh.pm.ormorange.orangeUtil.ReflectionUtil;

public class DefaultFieldInflater extends FieldInflater {

    public DefaultFieldInflater( Field field, Cursor cursor, Object object, Class<?> fieldType) {
        super(field, cursor, object, fieldType);
    }

    @Override
    public void inflate() {
        ReflectionUtil.setFieldValueFromCursor(cursor, field, object);
    }
}
