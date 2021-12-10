package com.example.toiquewordbook;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewFragment extends Fragment {

    QuizActivity quizActivity;

    private TextView quizScore;

    private RecyclerView recyclerView;
    private WordListAdapter adapter;
    private ArrayList<Word> list;
    //private DBQueryManager wrongWordList = new DBQueryManager("CHECKED");

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_review,container, false);
        quizActivity = (QuizActivity) getActivity();

        quizScore =  (TextView) v.findViewById(R.id.quizScore);
        quizScore.setText(quizActivity.score+"점");

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        //list = wrongWordList.getWordList(getContext());
        recyclerView.setHasFixedSize(true);
        adapter = new WordListAdapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        return v;
    }
}