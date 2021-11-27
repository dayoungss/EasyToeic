package com.example.toiquewordbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.toiquewordbook.R;

public class HomeFragment extends Fragment implements View.OnClickListener{

    ImageView settings;
    TextView daily_word;  // 홈화면 오늘의 영단어
    TextView daily_meaning; // 홈화면 오늘의 영단어 뜻
    ProgressBar total_progressbar; // 홈화면 학습진행율

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        settings = (ImageView) v.findViewById(R.id.settings);
        daily_word = (TextView) v.findViewById(R.id.daily_word);
        daily_meaning = (TextView) v.findViewById(R.id.daily_meaning);
        total_progressbar = (ProgressBar) v.findViewById(R.id.total_progressbar);

        daily_word = (TextView) v.findViewById(R.id.daily_word);
        daily_word.setText("Hello");

        // 홈화면 진도율 표시...
        total_progressbar.setProgress(30);
        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);

    }


    @Override
    public void onClick(View view) {

    }

}