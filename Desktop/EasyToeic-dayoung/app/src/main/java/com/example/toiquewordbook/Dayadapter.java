package com.example.toiquewordbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dayadapter extends RecyclerView.Adapter<Dayadapter.CustomViewHolder> {
    private Context mContext;


    private ArrayList<dayData> arrayList;
    private Intent intent;

    public Dayadapter(Context context, ArrayList<dayData> arrayList) {
        this.arrayList = arrayList;
        this.mContext=context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dayitem,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Dayadapter.CustomViewHolder holder, int position) {
        holder.day.setText(arrayList.get(position).getDayString());

        holder.itemView.setTag(position);
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    Intent intent = new Intent (mContext, QuizActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // 어떤 날짜의 퀴즈인지 퀴즈 액티비티에 넘겨야 함
                    intent.putExtra("day", arrayList.get(pos).getDay());
                view.getContext().startActivity(intent);

            }
        });*/
    }


    @Override
    public int getItemCount() {

        return (null != arrayList ? arrayList.size():0);
    }

    public ArrayList<com.example.toiquewordbook.dayData>getArrayList(){
        return arrayList;
    }

    public void setArrayList(ArrayList<com.example.toiquewordbook.dayData>dayData){
        this.arrayList=dayData;
    }

    public void addItem(com.example.toiquewordbook.dayData data){

        arrayList.add(data);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView day;
        protected Button btn;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.day=(TextView) itemView.findViewById(R.id.day);
            this.btn=(Button) itemView.findViewById(R.id.btn_quiz);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent (mContext, CallWord.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        // 어떤 날짜의 단어장인지 넘겨야 함
                        intent.putExtra("day", arrayList.get(pos).getDayString());

                        mContext.startActivity(intent);
                    }
                }
            });

            this.btn.setOnClickListener(new View.OnClickListener() {

                // 퀴즈 액티비티로 이동하는 함수
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        Intent intent = new Intent (mContext, QuizActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        // 어떤 날짜의 퀴즈인지 퀴즈 액티비티에 넘겨야 함
                        intent.putExtra("day", arrayList.get(pos).getDay());

                        mContext.startActivity(intent);
                    }

                    //Intent intent = new Intent(getActivity(), QuizActivity.class);
                    //startActivity(intent);
                }
            }
            );
        }
    }
}