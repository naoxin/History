package com.result.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class ScUserDao {
    // 找到数据库
    private ScUserHelper helper;

    public ScUserDao(Context context) {
        helper = new ScUserHelper(context);
    }

    // 数据库的添加方法
    public boolean insert(String data, String title, String image) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("data", data);
        values.put("title", title);
        values.put("image", image);
        long insert = db.insert("scusers", null, values);
        if (insert != -1) {
            return true;
        } else {
            return false;
        }
    }

    // 数据库的查看方法
    public List<ScUser> select() {
        List<ScUser> list = new ArrayList<ScUser>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor rs = db.query("scusers", null, null, null, null, null, null);
        while (rs.moveToNext()) {
            ScUser user = new ScUser();
            int id = rs.getInt(rs.getColumnIndex("id"));
            String data = rs.getString(rs.getColumnIndex("data"));
            String title = rs.getString(rs.getColumnIndex("title"));
            String image = rs.getString(rs.getColumnIndex("image"));
            user.setId(id);
            user.setData(data);
            user.setTitle(title);
            user.setImage(image);
            list.add(user);
        }
        return list;
    }

    // 数据库的删除方法
    public boolean delete(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int delete = db.delete("scusers", "id = ?", new String[]{id + ""});
        if (delete > 0) {
            return true;
        } else {
            return false;
        }
    }



}
