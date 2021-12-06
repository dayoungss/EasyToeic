package com.example.toiquewordbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReviewFragment extends Fragment {

    private TextView quizScore;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_exam,container, false);

        quizScore =  (TextView) v.findViewById(R.id.quizScore);

        return v;
    }
}