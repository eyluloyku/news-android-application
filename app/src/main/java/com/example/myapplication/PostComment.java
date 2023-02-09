package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;

public class PostComment extends AppCompatActivity {
    EditText editTextPersonName;
    EditText editTextComment;
    TextView txtFailed;
    Button btnPostCmt;
    int id;
    ProgressBar prgBarPostCmt;

    Handler sayHelloHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            String retVal = msg.obj.toString();
            if (retVal.equals("1"))
            {

                Intent i = new Intent(PostComment.this, CommentsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("news_id", id);
                startActivity(i);
            }
            else
            {
                String txt = "Comment couldn't be posted!";
                txtFailed.setText(txt);
                txtFailed.setVisibility(View.VISIBLE);
            }

            prgBarPostCmt.setVisibility(View.INVISIBLE);

            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);
        editTextComment = findViewById(R.id.editTextComment);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        btnPostCmt = findViewById(R.id.btnPostCmt);
        txtFailed = findViewById(R.id.txtFailed);
        prgBarPostCmt = findViewById(R.id.prgBarPostCmt);

        prgBarPostCmt.setVisibility(View.INVISIBLE);
        txtFailed.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Post Comment");

        btnPostCmt.setOnClickListener(v->{

            prgBarPostCmt.setVisibility(View.VISIBLE);

            NewsRepo repo = new NewsRepo();

            ExecutorService srv = ((NewsApp)getApplication()).srv;
            id = getIntent().getIntExtra("news_id",1);
            repo.PostComment(srv,sayHelloHandler, editTextPersonName.getText().toString(), editTextComment.getText().toString(),id);
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}

