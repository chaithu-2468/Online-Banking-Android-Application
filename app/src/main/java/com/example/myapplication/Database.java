package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    String qry1 = "create table users(username text , email text , phone text , password text , pin text)";
    sqLiteDatabase.execSQL(qry1);

    String qry2 = "create table accounts(email text , phone text , balance Integer)";
    sqLiteDatabase.execSQL(qry2);

    String qry3 = "create table transcations(phone text ,  type text , amount Integer)";
    sqLiteDatabase.execSQL(qry3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS accounts");
        db.execSQL("DROP TABLE IF EXISTS transactions");

        // Call onCreate to recreate the tables
        onCreate(db);


    }

    public void register(String username , String email , String phone, String password){
        ContentValues cv = new ContentValues();
        cv.put("username" , username);
        cv.put("email" , email);
        cv.put("phone" , phone);
        cv.put("password" , password);
        cv.put("pin" , phone.substring(0,4));



        SQLiteDatabase db = getWritableDatabase();
        db.insert("users" , null , cv);

        //cv.clear();
        ContentValues value = new ContentValues();
        value.put("email" , email);
        value.put("phone" , phone);
        value.put("balance" , 5000);
        db.insert("accounts" , null , value);
        //db.close();

    }




    public int login(String username , String password){
        int res=0;
        String arr[] = new String[2];
        arr[0]=username;
        arr[1]=password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where phone=? and password=?" ,arr);
        if(c.moveToFirst()){
            res=1;
        }

        return res;
    }




    public Cursor checkbalance(String email){
        String arr[] = new String[1];
        arr[0]=email;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select balance from accounts where email=?" ,arr);
        return c;

    }



    public Cursor getdata (String phone)
    {
        SQLiteDatabase DB = this.getReadableDatabase();
        String arr[] = new String[1];
        arr[0]=phone;
        Cursor cursor = DB.rawQuery("Select * from transcations where phone=?", arr);
        return cursor;
    }

    public int displaybalance(String phone){
        SQLiteDatabase db = getReadableDatabase();
        String arr[] = new String[1];
        arr[0] = phone;
        Cursor c =db.rawQuery("select balance from accounts where phone=?" , arr);

        if(c != null && c.moveToFirst()){
            int balindex = c.getColumnIndex("balance");
            int balance = c.getInt(balindex);
            return balance;
        }
        else{
            return 0;
        }
    }

    public String pinprovider(String phone){
        SQLiteDatabase db = getReadableDatabase();
        String[] arr = new String[1];
        arr[0] = phone;

        Cursor c = db.rawQuery("select pin from users where phone=?" , arr);

        if(c != null && c.moveToFirst()){
            int pinindex = c.getColumnIndex("pin");
            String pin = c.getString(pinindex);
            return pin;
        }
        else {
            return null;
        }
    }


    public void addTranscation(String fromphone , String type , int amount , String tophone){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("phone" , fromphone);
        cv.put("type" , type);
        cv.put("amount" ,amount);
        db.insert("transcations" ,null,cv);
        //Change  balance of fromphone
        updatesendbalance(fromphone , amount);

        if(type.equals("SEND")){
            type="RECEIVE";
        }
        else {
            type="SEND";
        }



        ContentValues values = new ContentValues();
        values.put("phone" , tophone);
        values.put("type" , type);
        values.put("amount" ,amount);
        db.insert("transcations" ,null,values);
        //Change  balance of tophone
        updatereceivebalance(tophone , amount);
    }

    public void updatesendbalance(String phone , int amount){
        int bal=displaybalance(phone);
        SQLiteDatabase db = getWritableDatabase();
        bal=bal-amount;
        ContentValues cv = new ContentValues();
        cv.put("balance" ,String.valueOf(bal));
        String where = "phone=?";
        String[] whereargs = {phone};
        db.update("accounts",cv,"phone=?" ,whereargs);

    }

    public void updatereceivebalance(String phone, int amount) {
        int bal = displaybalance(phone);
        SQLiteDatabase db = getWritableDatabase();
        bal = bal + amount;

        String updateQuery = "UPDATE accounts SET balance = ? WHERE phone = ?";
        String[] selectionArgs = {String.valueOf(bal), phone};

        db.execSQL(updateQuery, selectionArgs); // Include both the query and selectionArgs
    }

    public int pinvalidation(String enteredpin , String phone){
        String actualpin = new String(pinprovider(phone));
        if(actualpin.equals(enteredpin)){
            return 1;
        }
        else {
            return 0;
        }
    }


}



