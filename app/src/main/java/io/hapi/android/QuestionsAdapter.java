package io.hapi.android;

import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import io.hapi.android.models.Question;


public class QuestionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int INPUT_TYPE = 0;
    private static final int BINARY_TYPE = 1;

    private final List<Question> mQuestions;

    public QuestionsAdapter(List<Question> questions) {
        mQuestions = questions;
    }

    @Override
    public int getItemViewType(int position) {
        return mQuestions.get(position).isBinaryResponse() ? BINARY_TYPE : INPUT_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == INPUT_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_input, parent, false);
            return new InputTypeViewHolder(view);
        } else if (viewType == BINARY_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_binary, parent, false);
            return new BinaryTypeViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((QuestionBinder) holder).bind(mQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    private interface QuestionBinder {
        void bind(Question question);
    }

    public class InputTypeViewHolder extends RecyclerView.ViewHolder implements QuestionBinder {
        private TextInputLayout mQuestionLayout;

        public InputTypeViewHolder(View v) {
            super(v);
            mQuestionLayout = (TextInputLayout) v.findViewById(R.id.text_input_layout);
        }

        @Override
        public void bind(Question question) {
            mQuestionLayout.setHint(question.getQuestion());
        }
    }

    public class BinaryTypeViewHolder extends RecyclerView.ViewHolder implements QuestionBinder {
        private TextView mQuestionText;
        private Switch mQuestionSwitch;

        public BinaryTypeViewHolder(View v) {
            super(v);
            mQuestionSwitch = (Switch) v.findViewById(R.id.question_switch);
            mQuestionText = (TextView) v.findViewById(R.id.question_text);
        }

        @Override
        public void bind(Question question) {
            mQuestionText.setText(question.getQuestion());
            mQuestionSwitch.setOnCheckedChangeListener((view, isChecked) ->
                    question.setBinaryResponse(isChecked));
        }
    }
}
