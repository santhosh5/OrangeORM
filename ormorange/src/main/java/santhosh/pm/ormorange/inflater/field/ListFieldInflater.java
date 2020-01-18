package santhosh.pm.ormorange.inflater.field;

import android.database.Cursor;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import santhosh.pm.ormorange.OrangeORMRecord;
import santhosh.pm.ormorange.annotations.OneToMany;

public class ListFieldInflater extends FieldInflater {
    private static final String LOG_TAG = "ListFieldInflater";

    public ListFieldInflater( Field field, Cursor cursor, Object object, Class<?> fieldType) {
        super(field, cursor, object, fieldType);
    }

    @Override
    public void inflate() {
        if (field.isAnnotationPresent(OneToMany.class)) {
            try {
                Long objectId = cursor.getLong(cursor.getColumnIndex(("ID")));

                ParameterizedType genericListType = (ParameterizedType) field.getGenericType();
                Class<?> genericListClass = (Class<?>) genericListType.getActualTypeArguments()[0];
                String targetName = field.getAnnotation(OneToMany.class).targetField();
                field.set(object, OrangeORMRecord.findOneToMany(genericListClass, targetName, object, objectId));
            } catch (IllegalAccessException e) {
                Log.e(LOG_TAG, String.format("Error while inflating list field %s", field), e);
            }
        } else {
            Log.w(LOG_TAG, String.format("List field %s has not OneToMany annotation", field));
        }
    }
}
