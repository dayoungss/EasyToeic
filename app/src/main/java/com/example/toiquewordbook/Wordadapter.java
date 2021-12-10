package com.example.toiquewordbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Wordadapter extends RecyclerView.Adapter<Wordadapter.CustomViewHolder>{



    private Context context;
    private ArrayList<Word> wordList;
    boolean checkChecked = false;
    boolean mywordChecked = false;
    private DBQueryManager manager = new DBQueryManager("DAY_1");

    public Wordadapter(ArrayList<Word> wordList, Context context){
        this.wordList = wordList;
        this.context= context;
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

        holder.itemView.setTag(holder.getAdapterPosition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),WordInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("WORDINFO", wordList.get(holder.getAdapterPosition()).getEng());
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

                    if (checkChecked){
                        check.setImageResource(R.drawable.check_icon);
                        checkChecked=false;
                        manager.deleteWordFromChecked(context, "bear");
                    }
                    else {
                        check.setImageResource(R.drawable.check_icon_onclick);
                        checkChecked=true;
                        manager.copyWordToChecked(context, "bear");
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
                    }
                    else {
                        myword.setImageResource(R.drawable.star_icon_onclick);
                        mywordChecked=true;
                    }
                }
            });

        }
    }
}