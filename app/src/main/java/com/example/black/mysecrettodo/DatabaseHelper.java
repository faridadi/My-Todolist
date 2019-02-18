package com.example.black.mysecrettodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.black.mysecrettodo.model.User;
import com.example.black.mysecrettodo.model.Todo;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "secret.db";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL("Create table user(id INTEGER PRIMARY KEY, password TEXT)");
        db.execSQL("Create table todo(id INTEGER PRIMARY KEY, note TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS todo");
        // create new tables
        onCreate(db);
    }

    //create user
    public boolean insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());

        // insert row
        long ins = db.insert("user", null, values);

        // assigning tags to todo
        if (ins == -1 ){
            return false;
        }else {
            return true;
        }
    }

    //create todo
    public boolean insertTodo(Todo todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note", todo.getNote());
        // insert row
        long ins = db.insert("note", null, values);

        // assigning tags to todo
        if (ins == -1 ){
            return false;
        }else {
            return true;
        }
    }

    // update todo
    public int updateToDo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note", todo.getNote());
        // updating row
        return db.update("todo", values, "id"+ " = ?", new String[] { String.valueOf(todo.getId()) });
    }

    //delete todo by id
    public void deleteToDo(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("note", "id" + " = ?",
                new String[] { String.valueOf(tado_id) });
    }

    //fetch all data todo
    public List<Todo> getAllToDos() {
        List<Todo> todos = new ArrayList<Todo>();
        String selectQuery = "SELECT  * FROM 'todo'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Todo td = new Todo();
                td.setId(c.getInt((c.getColumnIndex("id"))));
                td.setNote((c.getString(c.getColumnIndex("todo"))));
                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }
        return todos;
    }

    //mengecek data user apakah sudah ada didalam database
    public boolean cekUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user";
        Cursor c = db.rawQuery(query,null);
        if (c != null && c.getCount() > 0){
            return true;
        }else {
            //jika data user tidak ada maka akan mmembuat user baru dengan password default "admin"
            insertUser(new User("admin"));
            return false;
        }
    }

    //method untuk login
    public boolean login( String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user where password='"+pass+"'";
        Cursor c = db.rawQuery(query,null);
        if (c != null && c.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    //method untuk ganti password
    public boolean changePassword(String oldPass, String newPass){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM user where password='"+oldPass+"'";
        Cursor c = db.rawQuery(query,null);
        ContentValues values = new ContentValues();
        if (c != null && c.getCount() > 0){
            values.put("password", newPass);
            // updating row
            db.update("user", values, "password"+ " = ?", new String[] { oldPass });
            return true;
        }else {
            return false;
        }
    }

    public long createToDo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note", todo.getNote());
        // insert row
        long todo_id = db.insert("note", null, values);
        // assigning tags to todo
        return todo_id;
    }
}