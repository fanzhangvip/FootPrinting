package com.qhn.bhne.footprinting;

import android.app.Application;
import android.content.Context;

import com.qhn.bhne.footprinting.db.DaoMaster;
import com.qhn.bhne.footprinting.db.DaoSession;

import org.greenrobot.greendao.database.Database;


/**
 * Created by qhn
 * on 2016/11/16 0016.
 */

public class App extends Application {
    public static final boolean ENCRYPTED=true;
    private DaoSession daoSession;
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=this;
        initDB();

    }

    private void initDB() {
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,
                ENCRYPTED?"notes-db-encrypted":"notes-db");
        Database db=ENCRYPTED?helper.getEncryptedWritableDb("super-secret"):helper.getWritableDb();
        daoSession=new DaoMaster(db).newSession();
    }
}