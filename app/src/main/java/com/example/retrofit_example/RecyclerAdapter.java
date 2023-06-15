package com.example.retrofit_example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<User> data;

    public RecyclerAdapter(Context context, List<User> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = data.get(position);
        String name = user.getId();
        String fname = user.getUserId();
        String lname = user.getTitle();
        String email = user.getBody();

        holder.txt1.setText(name);
        holder.txt2.setText(fname);
        holder.txt3.setText(lname);
        holder.txt4.setText(email);

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdateScreen.class);
                context.startActivity(i);
            }
        });

        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, data.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt1, txt2, txt3, txt4;
        ImageView img1, img2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.name_txt);
            txt2 = itemView.findViewById(R.id.fname_txt);
            txt3 = itemView.findViewById(R.id.lname_txt);
            txt4 = itemView.findViewById(R.id.email_txt);

            img1 = itemView.findViewById(R.id.edit_btn);
            img2 = itemView.findViewById(R.id.delete_btn);
        }
    }
}
