package com.example.toiquewordbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HomeFragment extends Fragment implements View.OnClickListener{

    ImageButton settings;
    TextView daily_word;  // 홈화면 오늘의 영단어
    TextView daily_meaning; // 홈화면 오늘의 영단어 뜻
    ProgressBar total_progressbar; // 홈화면 학습진행율
    TextView progress_percentage;

    public HomeFragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        settings = (ImageButton) v.findViewById(R.id.settings);
        daily_word = (TextView) v.findViewById(R.id.daily_word);
        daily_meaning = (TextView) v.findViewById(R.id.daily_meaning);
        total_progressbar = (ProgressBar) v.findViewById(R.id.total_progressbar);
        progress_percentage = (TextView) v.findViewById(R.id.progress_percentage);


        setDailyWord();
        setTotalProgressbar();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);

    }
    // 오늘의 단어 (영어, 뜻) 설정
    public void setDailyWord(){
        daily_word.setText("Apple");
    }

    // 전체 학습률 설정
   public void setTotalProgressbar(){
        int value=calcRate(getContext());
        total_progressbar.setProgress(value);
        progress_percentage.setText(Integer.toString(value)+"%");

    }

    public int calcRate(Context context) {
        DBQueryManager D1 = new DBQueryManager("DAY_1");
        DBQueryManager D2 = new DBQueryManager("DAY_2");
        DBQueryManager checked = new DBQueryManager("CHECKED");

        double sum = D1.getMaxWordId(context)+D2.getMaxWordId(context);
        double result = checked.getMaxWordId(context)/(sum+checked.getMaxWordId(context));

        return (int)(result*100);
//        return checked.getMaxWordId(context);
    }

    @Override
    public void onClick(View view) {

    }


}