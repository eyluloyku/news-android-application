package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    RecyclerView recView;
    int id;
    ProgressBar prgBarComments;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<Comments> data = (List<Comments>)msg.obj;
            CommentsAdapter adp = new CommentsAdapter(CommentsActivity.this,data);
            recView.setAdapter(adp);
            recView.setVisibility(View.VISIBLE);
            prgBarComments.setVisibility(View.INVISIBLE);

            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        prgBarComments = findViewById(R.id.prgBarComments);
        recView = findViewById(R.id.comments_list);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setVisibility(View.INVISIBLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Comments");

        prgBarComments.setVisibility(View.VISIBLE);
        id = getIntent().getIntExtra("news_id",1);
        NewsRepo repo = new NewsRepo();
        repo.GetCommentsByNewsId(((NewsApp) getApplication()).srv, dataHandler, id);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comments_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        else if(item.getItemId()==R.id.postCom)
        {
            Intent i = new Intent(CommentsActivity.this,PostComment.class);
            i.putExtra("news_id", id);
            startActivity(i);
        }
        return true;
    }
}

