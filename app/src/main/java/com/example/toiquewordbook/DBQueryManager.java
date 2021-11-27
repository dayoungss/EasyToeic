package com.example.toiquewordbook;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBQueryManager {

    private static final String TABLE = "WORDTABLE";
//    private static DBQueryManager mInstance = new DBQueryManager();

    private DBQueryManager() {

    }

//    public static DBQueryManager getInstance() {
//        return mInstance;
//    }

    public static ArrayList<Word> getWordList(Context context) {
        Log.e("wow", "halo~~~");
        ArrayList<Word> resultList = new ArrayList<>();

        DBOpenHelper dbHelper = new DBOpenHelper(context);
        String query = "SELECT _id, eng, kor, isChecked FROM " + TABLE;
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

    public static void addWord(Context context, Word word) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues row = new ContentValues();

            int id = 0;
            if (word._id > 0) {
                id = word._id;
            } else {
            id = getMaxWordId(context);
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

    public static void updateWord(Context context, Word word) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues row = new ContentValues();

//            int id = 0;
//            if (word._id > 0) {
//                id = word._id;
//            } else {
            int id = getMaxWordId(context);

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

    public static void deleteWord(Context context, Word word) {
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

    private static int getMaxWordId(Context context) {
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
        result = result + 1;

        return result;
    }
}
