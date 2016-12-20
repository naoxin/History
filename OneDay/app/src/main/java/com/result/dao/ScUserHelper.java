package com.result.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ScUserHelper extends SQLiteOpenHelper {

    public ScUserHelper(Context context) {
        super(context, "scuser.db", null, 1);
    }

    // 创建一个数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table scusers(id integer primary key autoincrement,data varchar(20),title varchar(20),image varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}