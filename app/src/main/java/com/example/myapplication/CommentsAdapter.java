package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommViewHolder> {
    private Context ctx;
    private List<Comments> data;

    public CommentsAdapter(Context ctx, List<Comments> data){
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public CommViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root= LayoutInflater.from(ctx).inflate(R.layout.comment_rows,parent,false);
        CommViewHolder holder = new CommViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommViewHolder holder, int position) {

        holder.txtNameComments.setText(data.get(holder.getAdapterPosition()).getName());
        holder.txtMsgComments.setText(data.get(holder.getAdapterPosition()).getText());
        NewsApp app = (NewsApp)((AppCompatActivity)ctx).getApplication();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CommViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtNameComments;
        TextView txtMsgComments;
        ConstraintLayout row;

        public CommViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameComments = itemView.findViewById(R.id.txtNameComment);
            txtMsgComments = itemView.findViewById(R.id.txtMsgComment);
            row = itemView.findViewById(R.id.com_row);

        }
    }
}

