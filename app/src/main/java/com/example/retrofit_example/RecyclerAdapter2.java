package com.example.retrofit_example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {

    Context context;
    List<User> data;

    public RecyclerAdapter2(Context context, List<User> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter2.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = data.get(position);
        String name = user.getId();
        String fname = user.getUserId();
        String lname = user.getTitle();
        String email = user.getBody();

        holder.txt1.setText(name);
        holder.txt2.setText(fname);
        holder.txt3.setText(lname);
        holder.txt4.setText(email);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt1, txt2, txt3, txt4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.name_txt);
            txt2 = itemView.findViewById(R.id.fname_txt);
            txt3 = itemView.findViewById(R.id.lname_txt);
            txt4 = itemView.findViewById(R.id.email_txt);


        }
    }
}
