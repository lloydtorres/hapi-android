package io.hapi.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.hapi.android.models.Question;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class PlusMinusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER = 0;
    private static final int STORY = 1;

    private List<Object> cards;

    public PlusMinusAdapter(List<Object> o)
    {
        cards = o;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case HEADER:
                View headerCard = inflater.inflate(R.layout.card_header, parent, false);
                viewHolder = new HeaderCard(headerCard);
                break;
            default:
                View storyCard = inflater.inflate(R.layout.card_story, parent, false);
                viewHolder = new StoryCard(storyCard);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case HEADER:
                HeaderCard headerCard = (HeaderCard) holder;
                headerCard.init((String) cards.get(position));
                break;
            default:
                StoryCard storyCard = (StoryCard) holder;
                storyCard.init((Question) cards.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (cards.get(position) instanceof String)
        {
            return HEADER;
        }
        else if (cards.get(position) instanceof Question)
        {
            return STORY;
        }

        return -1;
    }

    public class HeaderCard extends RecyclerView.ViewHolder {

        private TextView header;

        public HeaderCard(View v) {
            super(v);
            header = (TextView) v.findViewById(R.id.header_content);
        }

        public void init(String s)
        {
            header.setText(s);
        }
    }

    public class StoryCard extends RecyclerView.ViewHolder {

        private TextView sQuestion;
        private TextView sResponse;

        public StoryCard(View v) {
            super(v);
            sQuestion = (TextView) v.findViewById(R.id.story_question);
            sResponse = (TextView) v.findViewById(R.id.story_response);
        }

        public void init(Question q)
        {
            sQuestion.setText(q.getQuestion());
            sResponse.setText(String.valueOf(q.getResponse()));
        }
    }
}
