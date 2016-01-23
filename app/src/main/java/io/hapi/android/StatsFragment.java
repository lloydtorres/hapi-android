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
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mRecyclerAdapter;

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

        List<Integer> randomNumbers = new ArrayList<Integer>() {{
            add(36);
            add(61);
            add(15);
            add(90);
            add(73);
            add(16);
            add(89);
            add(56);
            add(63);
            add(39);
            add(23);
        }};
        EmotionHistory history = new EmotionHistory(randomNumbers);
        Statistic s = new Statistic("Days since you started", 10);

        randomStuff.add(history);
        randomStuff.add(s);

        mRecyclerAdapter = new StatsAdapter(randomStuff);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return view;
    }
}
