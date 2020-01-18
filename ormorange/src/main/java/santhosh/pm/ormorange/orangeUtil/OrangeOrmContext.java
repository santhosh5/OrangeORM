package santhosh.pm.ormorange.orangeUtil;

import android.content.Context;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import santhosh.pm.ormorange.helper.ContextUtil;

public class OrangeOrmContext {

    private static OrangeORMDbConfig dbConfiguration = null;
    private static OrangeOrmContext instance = null;
    private OrangeORMDb sugarDb;
    private Map<Object, Long> entitiesMap;

    private OrangeOrmContext() {
        this.sugarDb = OrangeORMDb.getInstance();
        this.entitiesMap = Collections.synchronizedMap(new WeakHashMap<Object, Long>());
    }

    public static OrangeOrmContext getOrangeORMContext () {
        if (instance == null) {
            throw new NullPointerException("SugarContext has not been initialized properly. Call SugarContext.init(Context) in your Application.onCreate() method and SugarContext.terminate() in your Application.onTerminate() method.");
        }
        return instance;
    }

    public static void init( Context context) {
        ContextUtil.init(context);
        instance = new OrangeOrmContext();
        dbConfiguration = null;
    }

    public static void init(Context context, OrangeORMDbConfig configuration) {
        init(context);
        dbConfiguration = configuration;
    }


    public static void terminate() {
        if (instance == null) {
            return;
        }
        instance.doTerminate();
        ContextUtil.terminate();
    }

    /*
     * Per issue #106 on Github, this method won't be called in
     * any real Android device. This method is used purely in
     * emulated process environments such as an emulator or
     * Robolectric Android mock.
     */
    private void doTerminate() {
        if (this.sugarDb != null) {
            this.sugarDb.getDB().close();
        }
    }

    public static OrangeORMDbConfig getDbConfiguration() {
        return dbConfiguration;
    }

    public OrangeORMDb getOrangeORMDb () {
        return sugarDb;
    }

    public Map<Object, Long> getEntitiesMap() {
        return entitiesMap;
    }

}
