package com.example.toiquewordbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Word> wordList;

    public WordListAdapter(Context context, ArrayList<Word> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_wordlist, parent, false);
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
        TextView engpron;
        TextView kor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eng = (TextView)itemView.findViewById(R.id.eng);
            engpron = (TextView)itemView.findViewById(R.id.engpron);
            kor = (TextView)itemView.findViewById(R.id.kor);



        }

        void onBind(Word word) {
            eng.setText(word.getEng());
            engpron.setText("["+word.getEngpron()+"]");
            kor.setText(word.getKor());
        }
    }

}
