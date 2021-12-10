package com.example.toiquewordbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_callwords);

        getIntent = getIntent();// 인텐트 받아오기
        day = getIntent.getStringExtra("day");

        //어떤 단어를 넘기는지 알려야함.

        arrayList = new ArrayList<>();

        DBQueryManager manager = new DBQueryManager(day);
        arrayList = manager.getWordList(this);

        recyclerView = findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        wordAdapter = new Wordadapter(arrayList, this);
        recyclerView.setAdapter(wordAdapter);

    }




}