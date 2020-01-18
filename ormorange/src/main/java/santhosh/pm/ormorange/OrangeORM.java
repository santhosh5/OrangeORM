package santhosh.pm.ormorange;

import android.app.Application;

import santhosh.pm.ormorange.helper.ContextUtil;


public class OrangeORM extends Application  {

    @Override
    public void onCreate ( ) {
        super.onCreate();
        ContextUtil.init(this);
    }

    @Override
    public void onTerminate ( ) {
        super.onTerminate();
        ContextUtil.terminate();
    }
}
