package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {
    ArrayList<News> news = new ArrayList<>();
    NewsAdapter adp;
    RecyclerView recyclerView;
    Context ctx;
    ProgressBar prgBarMain;
    int id;
    public NewsFragment(){}

    public NewsFragment(int id)
    {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prgBarMain = view.findViewById(R.id.prgBarMain);
        prgBarMain.setVisibility(View.VISIBLE);

        NewsRepo repo = new NewsRepo();
        recyclerView = view.findViewById(R.id.recycler_view);
        repo.getNewsByCategoryId(((NewsApp)getActivity().getApplication()).srv,dataHandler,id);



    }
    Handler dataHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            news = (ArrayList<News>)msg.obj;
            adp = new NewsAdapter(getActivity(), news);
            recyclerView.setAdapter(adp);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            prgBarMain.setVisibility(View.INVISIBLE);
            return true;
        }
    });
}