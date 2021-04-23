package com.sssakib.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User.bd";
    private static final String TABLE_NAME = "user_details";
    private static final int DATABASE_VERSION = 8;
    private Context context;

    private static final String ID = "_id";
    private static final String NAME = "Name";
    private static final String MOBILE = "Mobile";
    private static final String EMAIL = "Email";
    private static final String ADDRESS = "Address";
    private static final String PASSWORD = "Password";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + MOBILE + " TEXT, " + EMAIL + " TEXT, " + ADDRESS + " TEXT, " + PASSWORD + " TEXT  )";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate Method is called", Toast.LENGTH_LONG).show();
        } catch (Exception e) {

            Toast.makeText(context, "Exception :" + e, Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
            Toast.makeText(context, "onUpgrade Method is called", Toast.LENGTH_LONG).show();
        } catch (Exception e) {

            Toast.makeText(context, "Exception :" + e, Toast.LENGTH_LONG).show();

        }

    }

    public void insertData(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, user.getName());
        contentValues.put(MOBILE, user.getMobile());
        contentValues.put(EMAIL, user.getEmail());
        contentValues.put(ADDRESS, user.getAddress());
        contentValues.put(PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public boolean checkUser(String mobile){
        String[] columns = {
                ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = MOBILE + " = ?";
        String[] selectionArgs = { mobile };

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String mobile, String password){
        String[] columns = {
                ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = MOBILE + " = ?" + " AND " + PASSWORD + " =?";
        String[] selectionArgs = { mobile, password };

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }


}
