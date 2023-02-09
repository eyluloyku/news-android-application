package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private PageAdapter pageAdapter;
    ArrayList<NewsCategories> categories = new ArrayList<>();
    ArrayList<NewsFragment> fragments = new ArrayList<>();
    ProgressBar prgBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("NewsApp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);
        viewPager2 = findViewById(R.id.pager);
        prgBar = findViewById(R.id.prgBar);
        prgBar.setVisibility(View.VISIBLE);
        tabLayout = findViewById(R.id.tabLayout);
        pageAdapter = new PageAdapter(this);
        NewsRepo repo = new NewsRepo();
        repo.GetAllNewsCategories(((NewsApp)getApplication()).srv, dataHandler);
    }
    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            ArrayList<NewsCategories> cat = (ArrayList<NewsCategories>)msg.obj;
            for(int i = 0; i< cat.size();i++)
            {
                categories.add(cat.get(i));
                fragments.add(i, new NewsFragment(cat.get(i).getId()));
            }
            pageAdapter.setData(fragments);
            viewPager2.setAdapter(pageAdapter);
            new TabLayoutMediator(tabLayout, viewPager2,
                    (tab, position) -> tab.setText(categories.get(position).getName())
            ).attach();

            prgBar.setVisibility(View.INVISIBLE);
            return true;

        }
    });
}