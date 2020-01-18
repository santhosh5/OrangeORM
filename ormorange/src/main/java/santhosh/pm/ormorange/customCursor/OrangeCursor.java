package santhosh.pm.ormorange.customCursor;

import android.database.Cursor;
import android.database.CursorWrapper;

public class OrangeCursor extends CursorWrapper {

    public OrangeCursor( Cursor cursor) {
        super(cursor);
    }

    @Override
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        try {
            return super.getColumnIndexOrThrow(columnName);
        } catch (IllegalArgumentException e) {
            if (columnName.equals("_id"))
                return super.getColumnIndexOrThrow("ID");
            else
                throw e;
        }
    }

    @Override
    public int getColumnIndex(String columnName) {
        if (columnName.equals("_id"))
            columnName = "ID";
        return super.getColumnIndex(columnName);
    }
}
