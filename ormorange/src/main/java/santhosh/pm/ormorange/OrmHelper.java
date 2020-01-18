package santhosh.pm.ormorange;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import santhosh.pm.ormorange.orangeUtil.OrangeOrmContext;

public class OrmHelper {

    public static void initOrangeORM(Context context) {
        OrangeOrmContext.init(context);
    }

    public static SQLiteDatabase getDbInstance(){
        return OrangeORMRecord.getOrangeORMDataBase();
    }
}
