package com.ranger.hellofd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemDao {

    private DbHelper helper;

    public ItemDao(Context context) {
        helper = new DbHelper(context, "test_db", null, 1);
    }

    public List<TodoItem> findAll() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<TodoItem> list = new ArrayList<TodoItem>();
        Cursor cursor = db.query("Item", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            TodoItem todoItem = new TodoItem(cursor.getInt(0), new Date(cursor.getLong(2)),
                    cursor.getString(1), cursor.getInt(3) == 1);
            list.add(todoItem);
        }
        db.close();
        cursor.close();
        return list;
    }

    public void loadAll(List<TodoItem> todoItems) {
        todoItems.clear();
        List<TodoItem> items = findAll();
        todoItems.addAll(items);
    }

    public boolean add(String content) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CREATE_TIME", new Date().getTime());
        values.put("CONTENT", content);
        values.put("CHECKED", false);
        long insert = db.insert("ITEM", null, values);
        db.close();
        return insert != -1;
    }

    public boolean add(String content, boolean checked) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CREATE_TIME", new Date().getTime());
        values.put("CONTENT", content);
        values.put("CHECKED", checked);
        long insert = db.insert("ITEM", null, values);
        db.close();
        return insert != -1;
    }

    public void updateChecked(int id, boolean checked) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update ITEM set CHECKED=? where ID=?", new Object[]{checked, id});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("ITEM", "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("ITEM", null, null);
        db.close();
    }

}
