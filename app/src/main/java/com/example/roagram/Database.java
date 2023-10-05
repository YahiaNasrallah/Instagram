package com.example.roagram;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "insta_database";

    //-----------------------------------------------------------------


    private static final String TABLE_USER = "User_Table";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_NAME_USER_NAME = "user_name";
    private static final String COLUMN_USER_BIO = "user_bio";
    private static final String COLUMN_USER_POSTS = "user_posts_num";
    private static final String COLUMN_USER_FOLLOWERS = "user_followers_num";
    private static final String COLUMN_USER_FOLLOWING = "user_following_num";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_Image = "user_image";


    //---------------------------------------------------------

    private static final String COLUMN_TEMP_NAME = "usernametemp";
    private static final String COLUMN_TEMP_ID = "idtemp";
    private static final String TABLE_TEMP = "User_Temp";



    public Database(Context context){
        super(context,DATABASE_NAME,null,4);

    }



    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  User_Table ( user_email TEXT unique primary key, user_password TEXT , username TEXT unique," +
                " user_image BLOB , user_name TEXT , user_bio TEXT , user_posts_num INTEGER ,user_followers_num INTEGER , user_following_num INTEGER ) ");

        db.execSQL("CREATE TABLE  User_Temp ( usernametemp TEXT , idtemp TEXT ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEMP);

        onCreate(db);
    }




    //---------------------USER------------------------

    public boolean AddUser(USER user){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME,user.getUsername());
        values.put(COLUMN_NAME_USER_NAME,user.getName());
        values.put(COLUMN_USER_BIO,user.getBio());
        values.put(COLUMN_USER_POSTS,user.getPosts_num());
        values.put(COLUMN_USER_FOLLOWERS,user.getFollwrs_num());
        values.put(COLUMN_USER_FOLLOWING,user.getFollwing_num());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());
        values.put(COLUMN_USER_EMAIL,user.getEmail());
        values.put(COLUMN_USER_Image,getBytes(user.getPhoto()));





        long userId = db.insert(TABLE_USER, null, values);

        db.close();
        return userId!=0;


    }
    @SuppressLint("Range")
    public boolean CheckUsername(String name) {
        SQLiteDatabase db = getReadableDatabase();

        boolean flag=false;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {


                if (cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)).equals(name)){
                    flag=true;
                }


                cursor.moveToNext();

            }
        }

        cursor.close();
        return flag;
    }

    @SuppressLint("Range")
    public boolean CheckUser(String name,String password) {
        SQLiteDatabase db = getReadableDatabase();

        boolean flag=false;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {


                if (cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)).equals(name)&&
                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)).equals(password)){
                    flag=true;
                }


                cursor.moveToNext();

            }
        }

        cursor.close();
        return flag;
    }

    @SuppressLint("Range")
    public boolean CheckUserByUsername(String name,String password) {
        SQLiteDatabase db = getReadableDatabase();

        boolean flag=false;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {


                if (cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)).equals(name)&&
                        cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)).equals(password)){
                    flag=true;
                }


                cursor.moveToNext();

            }
        }

        cursor.close();
        return flag;
    }

    @SuppressLint("Range")
    public ArrayList<USER> SearchAllUsersByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<USER> userArrayList = new ArrayList<>();



        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USERNAME + " LIKE '%" + name + "%'", null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                USER user = new USER();

                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER_NAME)));
                user.setBio(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BIO)));
                user.setFollwrs_num(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_FOLLOWERS)));
                user.setFollwing_num(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_FOLLOWING)));
                user.setPosts_num(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_POSTS)));
                user.setPhoto(getImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_Image))));


                userArrayList.add(user);
                cursor.moveToNext();

            }
        }

        cursor.close();
        return userArrayList;
    } @SuppressLint("Range")
    public USER getUserByEmail(String name) {
        SQLiteDatabase db = getReadableDatabase();
        USER user = new USER();



        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + " LIKE '%" + name + "%'", null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER_NAME)));
                user.setBio(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BIO)));
                user.setFollwrs_num(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_FOLLOWERS)));
                user.setFollwing_num(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_FOLLOWING)));
                user.setPosts_num(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_POSTS)));
                user.setPhoto(getImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_Image))));


                cursor.moveToNext();

            }
        }

        cursor.close();
        return user;
    }

    public boolean UpdateUSer(USER user,String name) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,user.getUsername());
        values.put(COLUMN_NAME_USER_NAME,user.getName());
        values.put(COLUMN_USER_BIO,user.getBio());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());
        values.put(COLUMN_USER_EMAIL,user.getEmail());
        values.put(COLUMN_USER_Image,getBytes(user.getPhoto()));

        String[] array = { name+""};
        long result = sqLiteDatabase.update(TABLE_USER,values,
                " " +COLUMN_USER_EMAIL + "=? ",array);


        return result>0;

    }

    public void deleteUser(String Name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USER+ " WHERE "+COLUMN_USERNAME+"='"+Name+"'");
        db.close();
    }
    //-----------------------TEMPUSER---------------------------
    public boolean AddTemp(String name){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TEMP_NAME,name);
        values.put(COLUMN_TEMP_ID,"1");

        long tempId = db.insert(TABLE_TEMP, null, values);

        db.close();
        return tempId!=0;


    }


    @SuppressLint("Range")
    public String getTemp() {
        SQLiteDatabase db = getReadableDatabase();
        String name="";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("Select * from " + TABLE_TEMP,null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                name=cursor.getString(cursor.getColumnIndex(COLUMN_TEMP_NAME));

                cursor.moveToNext();

            }
        }



        return name ;


    }



    public boolean UpdateTemp(String temp) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TEMP_NAME,temp);
        String[] array = { 1+""};
        long result = sqLiteDatabase.update(TABLE_TEMP,values,
                " " +COLUMN_TEMP_ID + "=? ",array);

        return result>0;

    }







    //------------------------------------------------
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
