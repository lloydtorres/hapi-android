package io.hapi.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.hapi.android.models.Question;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class StoryFragment extends Fragment {
    private String mTitle;
    private List<Question> questions;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mRecyclerAdapter;

    public void setTitle(String t)
    {
        mTitle = t;
    }

    public void setQuestions(List<Question> q) {
        questions = q;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Object> randomStuff = new ArrayList<Object>();

        randomStuff.add(mTitle);
        randomStuff.addAll(questions);

        mRecyclerAdapter = new PlusMinusAdapter(randomStuff);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return view;
    }
}
