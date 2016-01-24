package io.hapi.android;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import io.hapi.android.models.EmotionHistory;
import io.hapi.android.models.Statistic;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class StatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int EMOTION_HISTORY = 0;
    private static final int STATISTIC = 1;

    private List<Object> cards;

    public StatsAdapter(List<Object> o)
    {
        cards = o;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case EMOTION_HISTORY:
                View historyCard = inflater.inflate(R.layout.card_linechart, parent, false);
                viewHolder = new HistoryCard(historyCard);
                break;
            default:
                View statisticCard = inflater.inflate(R.layout.card_statistic, parent, false);
                viewHolder = new StatisticCard(statisticCard);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case EMOTION_HISTORY:
                HistoryCard historyCard = (HistoryCard) holder;
                historyCard.init((EmotionHistory) cards.get(position));
                break;
            default:
                StatisticCard statisticCard = (StatisticCard) holder;
                statisticCard.init((Statistic) cards.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (cards.get(position) instanceof EmotionHistory)
        {
            return EMOTION_HISTORY;
        }
        else if (cards.get(position) instanceof Statistic)
        {
            return STATISTIC;
        }

        return -1;
    }

    public class HistoryCard extends RecyclerView.ViewHolder {

        private LineChart chartHistory;

        public HistoryCard(View v) {
            super(v);
            chartHistory = (LineChart) v.findViewById(R.id.happiness_chart);
        }

        public void init(EmotionHistory h)
        {
            final float lineWidth = 4f;

            List<Integer> history = h.getHistory();
            List<Entry> entryHistory = new ArrayList<Entry>();

            // Build data
            for (int i=0; i < history.size(); i++)
            {
                entryHistory.add(new Entry(history.get(i), i));
            }

            LineDataSet dataSet = new LineDataSet(entryHistory, "History");
            dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            dataSet.setDrawHighlightIndicators(false);
            dataSet.setLineWidth(lineWidth);
            dataSet.setDrawValues(false);
            dataSet.setColor(Color.parseColor("#FF5722"));
            dataSet.setCircleColor(Color.parseColor("#FF5722"));

            List<String> xLabels = new ArrayList<String>();
            for (int i=0; i < entryHistory.size(); i++)
            {
                xLabels.add(String.format("Day %d", i+1));
            }
            LineData data = new LineData(xLabels, dataSet);

            // formatting
            Legend cLegend = chartHistory.getLegend();
            cLegend.setEnabled(false);

            XAxis xAxis = chartHistory.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelsToSkip(4);

            YAxis yAxisRight = chartHistory.getAxisRight();
            yAxisRight.setEnabled(false);

            chartHistory.setDoubleTapToZoomEnabled(false);
            chartHistory.setDescription("");
            chartHistory.setDragEnabled(true);
            chartHistory.setScaleYEnabled(false);
            chartHistory.setDrawGridBackground(false);

            chartHistory.setData(data);
            chartHistory.invalidate();
        }
    }

    public class StatisticCard extends RecyclerView.ViewHolder {

        private TextView statDesc;
        private TextView statCount;

        public StatisticCard(View v) {
            super(v);
            statDesc = (TextView) v.findViewById(R.id.statistic_info);
            statCount = (TextView) v.findViewById(R.id.statistic_num);
        }

        public void init(Statistic s)
        {
            statDesc.setText(s.getDescription());
            statCount.setText(String.valueOf(s.getValue()));
        }
    }
}
