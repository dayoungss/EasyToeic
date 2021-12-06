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
    private Intent intent;
    private String day;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_callwords);

        intent = getIntent();// 인텐트 받아오기
        day = intent.getStringExtra("day");
        arrayList = new ArrayList<>();

        DBQueryManager manager = new DBQueryManager(day);
        arrayList = manager.getWordList(this);

        recyclerView = findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        wordAdapter = new Wordadapter(arrayList);
        recyclerView.setAdapter(wordAdapter);



    }




}