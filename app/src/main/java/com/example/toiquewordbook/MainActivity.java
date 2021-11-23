package com.example.toiquewordbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    TextView daily_word;  // 홈화면 오늘의 영단어
    TextView daily_meaning; // 홈화면 오늘의 영단어 뜻
    ProgressBar total_progressbar; // 홈화면 학습진행율

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daily_word = (TextView) findViewById(R.id.daily_word);
        daily_meaning = (TextView) findViewById(R.id.daily_meaning);
        total_progressbar = (ProgressBar) findViewById(R.id.total_progressbar);

        // 프래그먼트 객체 선언
        Fragment wordbookFragment= new WordbookFragment();
        Fragment homeFragment = new HomeFragment();
        Fragment mybookFragment = new MybookFragment();

        //제일 처음 띄워줄 뷰를 세팅해줍니다. commit();까지 해줘야 합니다.
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();

        // 바텀 네비게이션 객체 선언
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // 바텀 네비게이션 클릭 리스너 설정
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case  R.id.tab1:
                        // replace(프레그먼트를 띄워줄 frameLayout, 교체할 fragment 객체)
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, wordbookFragment).commitAllowingStateLoss();
                        return  true;
                    case  R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
                        return  true;
                    case  R.id.tab3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, mybookFragment).commitAllowingStateLoss();
                        return  true;
                    default:
                        return false;
                }
            }
        });

        // 홈화면 진도율 표시... 아래 줄 주석 풀면 앱 실행 안됨 ㅠ
        //total_progressbar.setProgress(30);
    }

}