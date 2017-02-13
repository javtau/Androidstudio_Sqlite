package com.javier_lozano.sqlite.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.javier_lozano.sqlite.Objects.Contact;

import java.util.ArrayList;

/**
 * Created by javi0 on 06/02/2017.
 */

public class ContactDataSource {
    private Context mContext;
    private SqliteHelper mSQLiteHelper ;
    public ContactDataSource (Context context) {
        mContext = context;
        mSQLiteHelper = new SqliteHelper (mContext);
    }

    public SQLiteDatabase openReadable() {
        return mSQLiteHelper.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return mSQLiteHelper.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }

        //Insertar un contacto en la base de datos
    public void insertContact(Contact contacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        ContentValues args = new ContentValues();
        args.put(SqliteHelper.COLUMN_NAME, contacto.getName());
        args.put(SqliteHelper.COLUMN_MAIL, contacto.getEmail());

        database.insert(SqliteHelper.TABLE_NAME,null,args);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

   /* public void insertContact(Contact contacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        database.execSQL("INSERT INTO " +SqliteHelper.TABLE_NAME+
                        " VALUES " + "(null,'"+contacto.getName()+
                        "','"+contacto.getEmail()+"'");
                database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }*/

    //---borrar un contacto en la base de datos ---
    public void borrarContacto(long rowId) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        database.delete(SqliteHelper.TABLE_NAME,String.format("%s=%d",SqliteHelper.COLUMN_ID, rowId),null);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    //--- actualizar un contacto en la base de datos ---
    public void actualizarContacto(Contact contacto) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        ContentValues args = new ContentValues();
        args.put(SqliteHelper.COLUMN_NAME,contacto.getName());
        args.put(SqliteHelper.COLUMN_MAIL,contacto.getEmail());
        database.update(SqliteHelper.TABLE_NAME,args, String.format("%s=%d", SqliteHelper.COLUMN_ID,contacto.getId()),null);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    private int getIntFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }
    private String getStringFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);
    }
    private Float getFloatFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getFloat(columnIndex);
    }

    //select * from contacts

    public ArrayList<Contact> readContacts() {
        SQLiteDatabase database = openReadable();
        Cursor cursor = database.query( SqliteHelper.TABLE_NAME,
        new String[]{SqliteHelper.COLUMN_ID,
                SqliteHelper.COLUMN_NAME,
                SqliteHelper.COLUMN_MAIL},
                null, null, null, null, null );
        ArrayList<Contact> contactos= new ArrayList<Contact>();
        if (cursor.moveToFirst()) {
            do {
                Contact contacto = new Contact(
                        getIntFromColumnName(cursor, SqliteHelper.COLUMN_ID),
                        getStringFromColumnName(cursor, SqliteHelper.COLUMN_NAME),
                        getStringFromColumnName(cursor,SqliteHelper.COLUMN_MAIL)
                        );
                contactos.add(contacto );
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return contactos;
    }

    public Contact readContact(long rowId) {
        SQLiteDatabase database = openReadable();
        Contact contacto = new Contact();
        Cursor cursor = database.rawQuery("SELECT _id, nombre, email " + "FROM contactos " +
                        "WHERE "+SqliteHelper.COLUMN_ID +" = "+rowId,null);
        if (cursor.moveToFirst()) {
            contacto = new Contact (
                    getIntFromColumnName(cursor, SqliteHelper.COLUMN_ID),
                    getStringFromColumnName(cursor, SqliteHelper.COLUMN_NAME),
                    getStringFromColumnName(cursor,SqliteHelper.COLUMN_MAIL));
        }
        cursor.close();
        database.close();
        return contacto;
    }


}
