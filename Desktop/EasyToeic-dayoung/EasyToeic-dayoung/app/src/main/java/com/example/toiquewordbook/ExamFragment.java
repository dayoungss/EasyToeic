package com.example.toiquewordbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class ExamFragment extends Fragment {

    QuizActivity quizActivity;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private TextView quiz_question;
    private Button bt1, bt2, bt3, btNext, btEnd;
    private boolean bt1Pressed,bt2Pressed,bt3Pressed;
    private ProgressBar quiz_progressbar;
    private int day; // 날짜
    private int N=10; // 전체 문제 수
    private int cnt=1; // 푼 문제 수
    private int answerIndex;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    // 메인 액티비티 위에 올린다.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        quizActivity = (QuizActivity) getActivity();
    }

    // 메인 액티비티에서 내려온다.
    @Override
    public void onDetach() {
        super.onDetach();
        quizActivity = null;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_exam,container, false);
        quizActivity = (QuizActivity) getActivity() ;

        quiz_question = (TextView) v.findViewById(R.id.quiz_question);
        bt1 = (AppCompatButton) v.findViewById(R.id.bt1);
        bt2 = (AppCompatButton) v.findViewById(R.id.bt2);
        bt3 = (AppCompatButton) v.findViewById(R.id.bt3);
        btNext = (Button) v.findViewById(R.id.btNext);
        btEnd = (Button) v.findViewById(R.id.btEnd);
        quiz_progressbar = (ProgressBar) v.findViewById(R.id.quiz_progressbar);
        day = quizActivity.getDay();

        // 버튼이 하나만 선택되도록 하는 함수
        bt1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (bt2Pressed) bt2.setPressed(false);
                if (bt3Pressed) bt3.setPressed(false);
                bt1Pressed = true;
                bt2Pressed=false;
                bt3Pressed=false;
                bt1.setPressed(true);
                return true;
            }
        });

        bt2.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (bt1Pressed) bt1.setPressed(false);
                if (bt3Pressed) bt3.setPressed(false);
                bt2Pressed = true;
                bt1Pressed=false;
                bt3Pressed=false;
                bt2.setPressed(true);
                return true;
            }
        });

        bt3.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (bt1Pressed) bt1.setPressed(false);
                if (bt2Pressed) bt2.setPressed(false);
                bt3Pressed = true;
                bt1Pressed=false;
                bt2Pressed=false;
                bt3.setPressed(true);
                return true;
            }
        });

        // next 버튼 눌렀을 때 문제 바꾸는 함수
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });


        // 퀴즈 종료버튼 누르면 오답체크 페이지로 이동
        btEnd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                quizActivity.changeFragment();
            }
        });


        return v;

    }

    //next 버튼 누르면 단어 변경하는거
    public void change() {
        String question,answer;
        String[] choice = new String[3];
        // 문제 정답 확인하기

        cnt++;
        quiz_progressbar.setProgress(cnt*10);
        if (cnt == N) {
            btNext.setVisibility(View.GONE);
            btEnd.setVisibility(View.VISIBLE);
        }

        // DB에서 랜덤 단어 선택
        // String 초기화
        question="apple";
        answer="사과";
        Random rand = new Random();
        answerIndex = rand.nextInt(3);
        choice[0]=answer;
        choice[1]="바나나";
        choice[2]="포도";
        for(int i=0; i<3; i++) {
            if (choice[i]!="answer") {
                // DB에서 랜덤 오답 추출
                // choice[i] = "...";
            }
        }

        quiz_question.setText(question);
        bt1.setText(choice[0]);
        bt2.setText(choice[1]);
        bt3.setText(choice[2]);
    }
}