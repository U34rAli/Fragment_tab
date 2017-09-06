package com.example.umar.fragment_tab;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Umar on 7/24/2017.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "MyDBName10.db";
    public static final String CONTACTS_IMAGE = "image";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String RECENT_TABLE_NAME = "recent";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_ADDRESS = "address";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_BITMAP = "bitmap";
    public static final String CONTACTS_COLUMN_FAVOURITE = "favourite";
    private HashMap hp;

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
// TODO Auto-generated method stub
        sqLiteDatabase.execSQL(
                "create table contacts " +
                        "(id integer primary key AUTOINCREMENT, name text,phone text,image text , bitmap blob , favourite integer default 0" +
                        ", email text , address text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
// TODO Auto-generated method stub
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");

        onCreate(sqLiteDatabase);
    }

    public boolean insertContact(String image, String name, String phone, byte[] bitmap ,String email , String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("image", image);
        contentValues.put("bitmap", bitmap);

        contentValues.put("email", email);
        contentValues.put("address", address);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id=" + id + "", null);
        return res;
    }

    public ArrayList<Contact> getAllCotacts() {
        ArrayList<Contact> array_list = new ArrayList<Contact>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String image = res.getString(res.getColumnIndex(CONTACTS_IMAGE));
            String name = res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME));
            String phone = res.getString(res.getColumnIndex(CONTACTS_COLUMN_PHONE));
            byte[] bitmap = res.getBlob(res.getColumnIndex(CONTACTS_COLUMN_BITMAP));
            int id = res.getInt(res.getColumnIndex(CONTACTS_COLUMN_ID));
            int f = res.getInt(res.getColumnIndex(CONTACTS_COLUMN_FAVOURITE));
            String email = res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL));
            String address = res.getString(res.getColumnIndex(CONTACTS_COLUMN_ADDRESS));


            array_list.add(new Contact(image, name, phone, bitmap, id, f , email , address));
            res.moveToNext();
        }
        return array_list;
    }

    public boolean updateContactFavourite(Integer id, int favourite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("favourite", favourite);
        db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }



    public boolean updateContact(String phone , String name , String email , String address , String image , byte[] bitmap)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("address", address);
        contentValues.put("image", image);
        contentValues.put("bitmap", bitmap);
        db.update("contacts", contentValues, "phone = ? ", new String[]{phone});
        return true;
    }





    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }
}
