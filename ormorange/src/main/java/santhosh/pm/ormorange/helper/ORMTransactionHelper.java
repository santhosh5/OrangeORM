package santhosh.pm.ormorange.helper;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static santhosh.pm.ormorange.orangeUtil.OrangeOrmContext.getOrangeORMContext;


/**
 * ORMTransactionHelper class
 */
public final class ORMTransactionHelper {
    private static final String LOG_TAG = ORMTransactionHelper.class.getSimpleName();

    //Prevent instantiation..
    private ORMTransactionHelper () { }

    public static void doInTransaction(Callback callback) {
        final SQLiteDatabase database = getOrangeORMContext().getOrangeORMDb().getDB();
        database.beginTransaction();

        try {
            if (ManifestHelper.isDebugEnabled()) {
                Log.d(LOG_TAG, "Callback executing within transaction");
            }

            callback.manipulateInTransaction();
            database.setTransactionSuccessful();

            if (ManifestHelper.isDebugEnabled()) {
                Log.d(LOG_TAG, "Callback successfully executed within transaction");
            }
        } catch (Throwable e) {
            if (ManifestHelper.isDebugEnabled()) {
                Log.d(LOG_TAG, "Could execute callback within transaction", e);
            }
        } finally {
            database.endTransaction();
        }
    }

    public interface Callback {
        void manipulateInTransaction ( );
    }
}
