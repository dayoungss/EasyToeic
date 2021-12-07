package com.example.toiquewordbook;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBQueryManager {


    private String TABLE;

    public DBQueryManager(String TABLE) {
        this.TABLE = TABLE;
    }


    public static String getRandomMeans(Context context) {
        String mean;
        DBOpenHelper dbHelper = new DBOpenHelper(context);
        String query = "SELECT kor FROM MEANS ORDER BY RANDOM() LIMIT 1";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        mean = cursor.getString(0);
        return mean;
    }

    public ArrayList<Word> getWordList(Context context) {
        ArrayList<Word> resultList = new ArrayList<>();

        DBOpenHelper dbHelper = new DBOpenHelper(context);
        String query = "SELECT _id, eng, engpron, kor, sentence FROM " + TABLE;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                Word word = new Word(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                resultList.add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbHelper.close();
        return resultList;
    }


    public void addWord(Context context, Word word) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues row = new ContentValues();

            int id = 0;
            if (word._id > 0) {
                id = word._id;
            } else {

            id = getMaxWordId(context)+1;
            }

            row.put("_id", id);
            row.put("eng", word.eng);
            row.put("kor", word.kor);

            db.insert(TABLE, null, row);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbHelper.close();
    }


    public void updateWord(Context context, Word word) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues row = new ContentValues();

//            int id = 0;
//            if (word._id > 0) {
//                id = word._id;
//            } else {

            int id = getMaxWordId(context)+1;

            row.put("_id", id);
            row.put("eng", word.eng);
            row.put("kor", word.kor);

            String strFilter = "_id = " + word._id;
            db.update(TABLE, row, strFilter, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        dbHelper.close();
    }


    public void deleteWord(Context context, Word word) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String strFilter = "_id = " + word._id;

            db.delete(TABLE, strFilter, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbHelper.close();
    }


    public int getMaxWordId(Context context) {
        int result = 0;
        DBOpenHelper dbHelper = new DBOpenHelper(context);
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT MAX(_id) FROM " + TABLE, null);

            while (cursor.moveToNext()) {
                result = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbHelper.close();

//        result += 1;

        return result;
    }

    public Word getSameEngWord(Context context, String eng) {

        DBOpenHelper dbHelper = new DBOpenHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT _id, eng, engpron, kor, sentence FROM ";
        Cursor cursor = db.rawQuery(query + TABLE, null);
        Word word=null;

        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(eng)) {
                word = new Word(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                dbHelper.close();
            }
        }

        return word;
    }

}
