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


    public ArrayList<Word> getWordList(Context context) {
        ArrayList<Word> resultList = new ArrayList<>();

        DBOpenHelper dbHelper = new DBOpenHelper(context);
        String query = "SELECT _id, eng, engpron, kor FROM " + TABLE;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                Word word = new Word(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
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

    public Word getSameEngWordList(Context context, String eng) {

        DBOpenHelper dbHelper = new DBOpenHelper(context);
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM" + TABLE, null);

            while(cursor.moveToNext()) {
                if(cursor.getString(1) == eng) {
                    Word word = new Word(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3));
                    return word;
                }
            }
        }  catch (Exception e) {
        e.printStackTrace();
        }
        dbHelper.close();
        return null;
    }

}
