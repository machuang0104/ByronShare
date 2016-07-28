package com.byron.library;

import android.app.Application;

import com.byron.library.exception.ExceptionHandler;
import com.byron.library.db.SQLiteDatabasePool;

/**
 * Custom Application
 *
 * @author Administrator
 */
@SuppressWarnings({"rawtypes", "deprecation"})
public class App extends Application {
    private final static String TAG = "App";

    private static App application;

    public static App getApp() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    }

    private SQLiteDatabasePool mSQLiteDatabasePool;

    public SQLiteDatabasePool getSQLiteDatabasePool() {
        if (mSQLiteDatabasePool == null) {
            mSQLiteDatabasePool = SQLiteDatabasePool.getInstance(this);
            mSQLiteDatabasePool.createPool();
        }
        return mSQLiteDatabasePool;
    }

    public void setSQLiteDatabasePool(SQLiteDatabasePool sqliteDatabasePool) {
        this.mSQLiteDatabasePool = sqliteDatabasePool;
    }
}