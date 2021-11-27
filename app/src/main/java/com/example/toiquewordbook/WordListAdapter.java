package com.example.toiquewordbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private ArrayList<Word> wordList;

    @NonNull
    @Override
    public WordListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordlist_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.ViewHolder holder, int position) {
        holder.onBind(wordList.get(position));
    }

    public void setWordList(ArrayList<Word> list){
        this.wordList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView eng;
        TextView kor;
        TextView check;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eng = (TextView)itemView.findViewById(R.id.eng);
            kor = (TextView)itemView.findViewById(R.id.kor);
            check = (TextView)itemView.findViewById(R.id.check);

        }

        void onBind(Word word) {
            eng.setText(word.getEng());
            kor.setText(word.getKor());
            check.setText(word.getIsChecked());
        }
    }

}
