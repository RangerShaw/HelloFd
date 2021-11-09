package com.ranger.hellofd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists 'ITEM'");
        String sql = "create table ITEM(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CONTENT CHAR(256) NOT NULL," +
                "CREATE_TIME LONG NOT NULL," +
                "CHECKED BOOLEAN NOT NULL)";
        db.execSQL(sql);
        db.execSQL("DELETE FROM sqlite_sequence WHERE name = 'ITEM'");
        db.delete("ITEM", null, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
