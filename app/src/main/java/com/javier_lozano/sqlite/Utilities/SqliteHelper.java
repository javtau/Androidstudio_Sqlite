package com.javier_lozano.sqlite.Utilities;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by javi0 on 06/02/2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    static final String COLUMN_ID = BaseColumns._ID;
    static final String COLUMN_NAME = "name";
    static final String COLUMN_MAIL = "email";
    static final String TABLE_NAME = "CONTACTS";
    static final String DATABASE_NAME = "ContactDB";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE_CONTACTS = "CREATE TABLE "+ TABLE_NAME+ "( "+
            COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            COLUMN_NAME+" TEXT NOT NULL," +
            COLUMN_MAIL+" TEXT NOT NULL);";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
