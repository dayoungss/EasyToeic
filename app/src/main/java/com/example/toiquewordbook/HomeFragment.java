package com.example.toiquewordbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    ImageView settings;
    TextView daily_word;  // 홈화면 오늘의 영단어
    TextView daily_meaning; // 홈화면 오늘의 영단어 뜻
    ProgressBar total_progressbar; // 홈화면 학습진행율

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        settings = (ImageView) v.findViewById(R.id.settings);
        daily_word = (TextView) v.findViewById(R.id.daily_word);
        daily_meaning = (TextView) v.findViewById(R.id.daily_meaning);
        total_progressbar = (ProgressBar) v.findViewById(R.id.total_progressbar);

        daily_word.setText("onCreate");

        settings.setOnClickListener(new View.OnClickListener() {

            //FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            @Override
            public void onClick(View view) {

                /*SettingsFragment settingsFragment = new SettingsFragment();
                transaction.replace(R.id.frame, settingsFragment);
                transaction.commit();*/

                daily_word.setText("클릭 이벤트");

                /*FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                SettingsFragment settingsFragment1 = new SettingsFragment();
                transaction.replace(R.id.frame_layout, settingsFragment1);
                transaction.commit();*/

                /*Intent intent = new Intent(getActivity().getApplicationContext(), SettingsFragment.class);
                startActivity(intent);*/
            }
        });

        // 홈화면 진도율 표시...
        total_progressbar.setProgress(30);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }



}