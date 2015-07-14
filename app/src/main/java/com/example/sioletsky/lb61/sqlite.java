package com.example.sioletsky.lb61;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SioletSky on 2015/7/9.
 */
public class sqlite extends SQLiteOpenHelper {
    public sqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE "+"tablename("
                +"name"+" TEXT NOT NULL,"
                +"weight"+" INTEGER NOT NULL,"
                +"tall"+" INTEGER NOT NULL)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
