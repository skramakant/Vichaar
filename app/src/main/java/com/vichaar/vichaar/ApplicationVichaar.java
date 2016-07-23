package com.vichaar.vichaar;

import android.app.Application;

import Database.DatabaseOpenHelper;

/**
 * Created by ramakant on 23/7/16.
 */
public class ApplicationVichaar extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseOpenHelper.getInstance(this);
    }
}
