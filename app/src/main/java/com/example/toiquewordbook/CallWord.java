package com.example.toiquewordbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallWord extends Activity {
    private RecyclerView recyclerView;
    private Wordadapter wordAdapter;
    private ArrayList<Word> arrayList;
    private LinearLayoutManager linearLayoutManager;

    private Intent getIntent;
    private Intent putIntent;
    private String day;
    String themeColor;

    private Context mContext;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callwords);

        //기본 SharedPreferences 환경과 관련된 객체를 얻어옵니다.
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        // SharedPreferences 수정을 위한 Editor 객체를 얻어옵니다.
        editor = preferences.edit();

        if (preferences.getBoolean("mode",false)){
            //dark mode
            Log.v("MODE", "CallWord dark mode");
            themeColor = ThemeUtil.DARK_MODE;
            ThemeUtil.applyTheme(themeColor);
            ThemeUtil.modSave(getApplicationContext(),themeColor);
        }else{
            //light mode
            Log.v("MODE", "CallWord light mode");
            themeColor = ThemeUtil.LIGHT_MODE;
            ThemeUtil.applyTheme(themeColor);
            ThemeUtil.modSave(getApplicationContext(),themeColor);
        }

        mContext=getApplicationContext();
        getIntent = getIntent();// 인텐트 받아오기
        day = getIntent.getStringExtra("day");
        getIntent= new Intent (this, Wordadapter.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getIntent.putExtra("day", day);

        //어떤 단어를 넘기는지 알려야함.
        arrayList = new ArrayList<>();

        DBQueryManager manager = new DBQueryManager(day);
        arrayList = manager.getWordList(this);

        recyclerView = findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        wordAdapter = new Wordadapter(arrayList, day,this);
        recyclerView.setAdapter(wordAdapter);

    }

}