package io.hapi.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.hapi.android.models.EmotionHistory;
import io.hapi.android.models.Statistic;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class StatsFragment extends Fragment {
    private List<Integer> scores;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mRecyclerAdapter;

    public void setScores(List<Integer> s)
    {
        scores = s;
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

        EmotionHistory history = new EmotionHistory(scores);
        Statistic s = new Statistic("Days since you started", 2);

        randomStuff.add(history);
        randomStuff.add(s);

        mRecyclerAdapter = new StatsAdapter(randomStuff);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return view;
    }
}
