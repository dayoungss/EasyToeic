package com.example.toiquewordbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WordbookFragment extends Fragment {



    private ArrayList<com.example.toeic.dayData> arrayList;
    private com.example.toiquewordbook.Dayadapter dayadapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_wordbook,container,false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=(RecyclerView) v.findViewById(R.id.rv);
        linearLayoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList=new ArrayList<>();

        for (int i =1;i<=40;i++){
            arrayList.add(new com.example.toeic.dayData("Day"+i));
        }


        dayadapter=new com.example.toiquewordbook.Dayadapter(arrayList);

        recyclerView.setAdapter(dayadapter);

        return inflater.inflate(R.layout.fragment_wordbook, container, false);

    }

    private void setContentView(int activity_main) {
    }

}