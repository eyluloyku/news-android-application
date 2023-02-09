package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityDetails extends AppCompatActivity {

    ImageView imageView2;
    TextView txtTitleDetails;
    TextView txtDateDetails;
    TextView txtNewsDetails;
    int id;

    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            News news = (News) msg.obj;

            txtTitleDetails.setText(news.getTitle());
            txtDateDetails.setText(news.getDate());
            txtNewsDetails.setText(news.getText());

            NewsRepo repo = new NewsRepo();
            repo.downloadImage(((NewsApp)getApplication()).srv, imgHandler,news.getImagePath());

            return true;
        }
    });

    Handler imgHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            Bitmap img = (Bitmap) msg.obj;
            imageView2.setImageBitmap(img);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        id = getIntent().getIntExtra("id",1);
        String catName = getIntent().getStringExtra("cat");
        imageView2  =findViewById(R.id.imageView2);
        txtTitleDetails = findViewById(R.id.txtTitleDetails);
        txtDateDetails = findViewById(R.id.txtDateDetails);
        txtNewsDetails = findViewById(R.id.txtNewsDetails);

        NewsRepo repo = new NewsRepo();
        repo.getNewsById(((NewsApp)getApplication()).srv,dataHandler,id);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(catName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        else if(item.getItemId()==R.id.shwCmts)
        {
            Intent i = new Intent(ActivityDetails.this,CommentsActivity.class);
            i.putExtra("news_id", id);
            startActivity(i);
        }
        return true;
    }
}
