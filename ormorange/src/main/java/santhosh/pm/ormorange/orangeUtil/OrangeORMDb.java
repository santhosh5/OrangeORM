package santhosh.pm.ormorange.orangeUtil;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import santhosh.pm.ormorange.customCursor.OrangeCursorFactory;
import santhosh.pm.ormorange.helper.ManifestHelper;
import static santhosh.pm.ormorange.helper.ContextUtil.getContext;
import static santhosh.pm.ormorange.helper.ManifestHelper.getDatabaseVersion;
import static santhosh.pm.ormorange.helper.ManifestHelper.getDbName;
import static santhosh.pm.ormorange.orangeUtil.OrangeOrmContext.getDbConfiguration;

public class OrangeORMDb extends SQLiteOpenHelper {
    private static final String LOG_TAG = "OrangeORM";

    private final SchemaGenerator schemaGenerator;
    private SQLiteDatabase sqLiteDatabase;
    private int openedConnections = 0;

    //Prevent instantiation
    private OrangeORMDb () {
        super(getContext(), getDbName(), new OrangeCursorFactory(ManifestHelper.isDebugEnabled()), getDatabaseVersion());
        schemaGenerator = SchemaGenerator.getInstance();
    }

    public static OrangeORMDb getInstance() {
        return new OrangeORMDb();
    }

    @Override
    public void onCreate( SQLiteDatabase sqLiteDatabase) {
        schemaGenerator.createDatabase(sqLiteDatabase);
    }

    @Override
    public void onConfigure( SQLiteDatabase db) {
        final OrangeORMDbConfig configuration = getDbConfiguration();

        if (null != configuration) {
            db.setLocale(configuration.getDatabaseLocale());
            db.setMaximumSize(configuration.getMaxSize());
            db.setPageSize(configuration.getPageSize());
        }

        super.onConfigure(db);
    }

    @Override
    public void onUpgrade( SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        schemaGenerator.doUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

    public synchronized SQLiteDatabase getDB() {
        if (this.sqLiteDatabase == null) {
            this.sqLiteDatabase = getWritableDatabase();
        }

        return this.sqLiteDatabase;
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        if(ManifestHelper.isDebugEnabled()) {
            Log.d(LOG_TAG, "getReadableDatabase");
        }
        openedConnections++;
        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        if(ManifestHelper.isDebugEnabled()) {
            Log.d(LOG_TAG, "getReadableDatabase");
        }
        openedConnections--;
        if(openedConnections == 0) {
            if(ManifestHelper.isDebugEnabled()) {
                Log.d(LOG_TAG, "closing");
            }
            super.close();
        }
    }
}
