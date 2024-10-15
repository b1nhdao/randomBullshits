package com.example.qlns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class dbManager {
    private Context context;
    private SQLiteDatabase db;
    private dbHelper helper;

    //viet phuong thuc khoi tao:
    public dbManager(Context context){
        this.context = context;
    }

    //viet phuong thuc de mo ket noi toi db
    public dbManager open(){
        helper = new dbHelper(context);
        db = helper.getWritableDatabase(); // lay ra doi tuong SQLiteDatabase de thao tao voi csdl
        return this;
    }

    //dong ket noi voi db
    public  void  close(){
        helper.close();
    }

    //xay dung phuong thuc INSERT & UPDATE
    //INSERT NOTE
    public void insertNote(Note note){
        ContentValues values = new ContentValues();
        values.put(helper.title, note.getTitle());
        values.put(helper.date, note.getCreateDate());
        values.put(helper.content, note.getContent());
        db.insert(helper.tbName, null, values);
    }

    //UPDATE NOTE
    public void updateNote(int id, String title, String date, String content){
        ContentValues values = new ContentValues();
        values.put(helper.title, title);
        values.put(helper.date, date);
        values.put(helper.content, content);
        db.update(helper.tbName, values, helper.id + "=" + id, null);
    }

    //DELETE NOTE
    public void deleteNote(int id){
        db.delete(helper.tbName, helper.id +"="+id, null);
    }

    //SELECT ALL
    public ArrayList<Note> getAllNotes(){
        ArrayList<Note> result = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + helper.tbName, null);
        if(cursor != null){
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String date = cursor.getString(2);
                String content = cursor.getString(3);
                result.add(new Note(id, title, date, content));
            }
        }
        return result;
    }

    //get note by id
    public Note getNoteByID(int id){
        Cursor cursor = db.rawQuery("SELECT * FROM" + helper.tbName + "WHERE" + helper.id + "=" + id, null);
        if (cursor != null){
            while(cursor.moveToNext()){
                int id1 = cursor.getInt(0);
                String title = cursor.getString(1);
                String date = cursor.getString(2);
                String content = cursor.getString(3);
                return new Note(id1, title, date, content);
            }
        }
        return  null;
    }
}
