package com.example.toiquewordbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Wordadapter extends RecyclerView.Adapter<Wordadapter.CustomViewHolder>{


    private ArrayList<Word> arrayList;

    public Wordadapter(ArrayList<Word> arrayList) {
        this.arrayList = arrayList;
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
        holder.word.setText(arrayList.get(position).getEng());
        holder.word_mean.setText(arrayList.get(position).getKor());
        // holder.check.setImageIcon();
        // holder.myword.setImageResource(arrayList.get(position).getMyword());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView word;
        protected TextView word_mean;
        //  protected ImageButton check;
        //  protected ImageButton myword;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.word =(TextView)itemView.findViewById(R.id.word);
            this.word_mean =(TextView)itemView.findViewById(R.id.word_mean);
            //    this.check = (ImageButton) itemView.findViewById(R.id.check_word);
            //    this.myword = (ImageButton) itemView.findViewById(R.id.my_word);

        }
    }
}