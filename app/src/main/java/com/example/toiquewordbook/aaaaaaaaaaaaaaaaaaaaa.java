package com.example.toiquewordbook;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class aaaaaaaaaaaaaaaaaaaaa extends Fragment {
    private RecyclerView recyclerView;
    private WordListAdapter wordListAdapter;
    private ArrayList<Word> wordlist;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_callwords,container,false);

        /* initiate adapter */
        recyclerView = v.findViewById(R.id.recyclerView);

        wordListAdapter = new WordListAdapter(getActivity(), wordlist);

        /* initiate recyclerview */
        recyclerView.setAdapter(wordListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        /* adapt data */
        wordlist = new ArrayList<>();
//        for (int i = 1; i <= 10; i++) {
//            wordlist.add(new Word(i, "eng " + i, "kor " + i, "1"));
//            wordListAdapter.setWordList(wordlist); }

        wordlist = new ArrayList<>();

        //wordlist = DBQueryManager.getWordList(getActivity());

        wordListAdapter.setWordList(wordlist);

        return v;
    }

}