package com.example.toiquewordbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallWord extends Fragment {
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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v("callword", "class start");

        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.activity_callwords,container,false);
        mContext=getContext();
        Log.v("callword", "getContext");


        //getIntent = getIntent();// 인텐트 받아오기
        day = getArguments().getString("day");
        getIntent= new Intent (mContext, Wordadapter.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getIntent.putExtra("day", day);
        Log.v("callword", day + " <- intent");


        //어떤 단어를 넘기는지 알려야함.
        arrayList = new ArrayList<>();

        DBQueryManager manager = new DBQueryManager(day);
        arrayList = manager.getWordList(mContext);

        recyclerView = v.findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext)) ;

        wordAdapter = new Wordadapter(arrayList, day,mContext);
        recyclerView.setAdapter(wordAdapter);
        Log.v("callword", "callword class over");

        return v;
    }

}