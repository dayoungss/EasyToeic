package com.example.toiquewordbook;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Wordadapter extends RecyclerView.Adapter<Wordadapter.CustomViewHolder>{



    private Context context;
    private ArrayList<Word> wordList;
    private String day;

    private DBQueryManager manager;

    public Wordadapter(ArrayList<Word> wordList, String day, Context context){
        this.wordList = wordList;
        this.context= context;
        this.day = day;
        manager = new DBQueryManager(day);
    }

    @NonNull
    @Override
    public Wordadapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_worditem,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Wordadapter.CustomViewHolder holder, int position) {
        holder.word.setText(wordList.get(holder.getAdapterPosition()).getEng());
        holder.word_mean.setText(wordList.get(holder.getAdapterPosition()).getKor());
        if (wordList.get(holder.getAdapterPosition()).getCheckedState()){
            holder.check.setImageResource(R.drawable.check_icon_onclick);
            holder.checkChecked=true;
        }

        if (wordList.get(holder.getAdapterPosition()).getMyWordState()){
            holder.myword.setImageResource(R.drawable.star_icon_onclick);
            holder.mywordChecked=true;
        }

        holder.wordName =  wordList.get(holder.getAdapterPosition()).getEng();
        holder.itemView.setTag(holder.getAdapterPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (context, WordInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("WORDINFO", wordList.get(holder.getAdapterPosition()).getEng());
                intent.putExtra("table", day);

                view.getContext().startActivity(intent);
            }
        });

    }

    public void setWordList(ArrayList<Word> list){
        this.wordList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null != wordList ? wordList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView word;
        protected TextView word_mean;
        protected ImageButton check;
        protected ImageButton myword;
        protected String wordName;

        boolean checkChecked = false;
        boolean mywordChecked = false;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.word =(TextView)itemView.findViewById(R.id.word);
            this.word_mean =(TextView)itemView.findViewById(R.id.word_mean);
            this.check = (ImageButton) itemView.findViewById(R.id.check_word);
            this.myword = (ImageButton) itemView.findViewById(R.id.my_word);
            // 체크 누르면 이미지 변경
            this.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (checkChecked){
                        check.setImageResource(R.drawable.check_icon);
                        checkChecked=false;
                    }
                    else {
                        check.setImageResource(R.drawable.check_icon_onclick);
                        checkChecked=true;
                    }
                }
            });

            // 즐겨찾기 (별) 누르면 이미지 변경
            this.myword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mywordChecked){
                        myword.setImageResource(R.drawable.star_icon);
                        mywordChecked=false;
                        manager.deleteWordFromMyWord(context, wordName);
                        if (day=="MYWORD"){
                            ((FragmentActivity)context).getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frame_layout, new MybookFragment()).addToBackStack("crop_type")
                                            .commit();
                            //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MybookFragment()).commit();
                        }
                    }
                    else {
                        myword.setImageResource(R.drawable.star_icon_onclick);
                        mywordChecked=true;
                        manager.copyWordToMyWord(context, wordName);
                    }
                }
            });

        }
    }
}