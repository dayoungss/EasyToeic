package com.example.toiquewordbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dayadapter extends RecyclerView.Adapter<Dayadapter.CustomViewHolder> {

    private ArrayList<com.example.toeic.dayData> arrayList;


    public Dayadapter(ArrayList<com.example.toeic.dayData> arrayList) {
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public Dayadapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dayitem,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Dayadapter.CustomViewHolder holder, int position) {
            holder.day.setText(arrayList.get(position).getDay());

            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String curName = holder.day.getText().toString();
                }
            });
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size():0);
    }

    public ArrayList<com.example.toeic.dayData>getArrayList(){
        return arrayList;
    }
    public void setArrayList(ArrayList<com.example.toeic.dayData>dayData){
        this.arrayList=dayData;
    }
    public void addItem(com.example.toeic.dayData data){
        arrayList.add(data);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView day;
        protected Button btn;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.day=(TextView) itemView.findViewById(R.id.day);
          //  this.btn=(Button) itemView.findViewById(R.id.btn_quiz);
        }
    }
}
