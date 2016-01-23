package io.hapi.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.hapi.android.models.Question;
import io.hapi.android.models.Questions;


public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Question> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public QuestionsAdapter(List<Question> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //TO DO DO
        RecyclerView.ViewHolder vh = new QuestionViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((QuestionViewHolder)holder).q.setText(mDataset.get(position).getQuestion());
    }

    // Replace the contents of a view (invoked by the layout manager)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        private TextView q;

        public QuestionViewHolder(View v) {
            super(v);

            q = (TextView) v.findViewById(R.id.info_text);
        }
    }
}