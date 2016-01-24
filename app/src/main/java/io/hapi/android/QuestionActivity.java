package io.hapi.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Date;
import java.util.List;

import io.hapi.android.models.Emotions;
import io.hapi.android.models.Entry;
import io.hapi.android.models.Question;
import io.realm.Realm;
import io.realm.RealmList;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QuestionActivity extends AppCompatActivity {

    private static final String EXTRA_PHOTO_URI = "MY EXTRA PHOTO URI" ;
    private RecyclerView mRecyclerView;
    private QuestionsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        getSupportActionBar().hide();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new QuestionsAdapter(QuestionProvider.getRandomQuestions(3));
        mRecyclerView.setAdapter(mAdapter);
        Uri imageUri = getIntent().getParcelableExtra(EXTRA_PHOTO_URI);
        ImageView userImage = (ImageView) findViewById(R.id.image_view);
        userImage.setImageURI(imageUri);
    }

    private List<Question> getAnsweredQuestions(){
        for (int i = 0; i < mAdapter.getQuestions().size(); i++) {
            RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForAdapterPosition(i);
            if (holder instanceof QuestionsAdapter.BinaryTypeViewHolder) {
                QuestionsAdapter.BinaryTypeViewHolder binaryType = (QuestionsAdapter.BinaryTypeViewHolder) holder;
                mAdapter.getQuestions().get(i).setBinaryResponse(binaryType.mQuestionSwitch.isChecked());
            } else if (holder instanceof QuestionsAdapter.InputTypeViewHolder) {
                QuestionsAdapter.InputTypeViewHolder inputType = (QuestionsAdapter.InputTypeViewHolder) holder;
                mAdapter.getQuestions().get(i).setResponse(inputType.mEditText.getText().toString());
            }
        }
        return mAdapter.getQuestions();
    }

    public void onSaveButtonClick(View v){
        final Intent intent = getIntent();
        if (intent != null) {
            Uri imageUri = intent.getParcelableExtra(EXTRA_PHOTO_URI);
            IndicoApi.getEmotions(imageUri)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(emotions -> saveEntry(imageUri,new Date(),getAnsweredQuestions(),emotions), error -> {
                        error.printStackTrace();
                    });
        }

    }



    private void saveEntry(Uri imageUri, Date date, List<Question> questions, Emotions emotions) {

        Entry entry = new Entry(imageUri.toString(),
                date,
                new RealmList<>(questions.toArray(new Question[questions.size()])),
                emotions);
        Realm realm = Realm.getInstance(this);
        // Persist your data easily
        realm.beginTransaction();
        realm.copyToRealm(entry);
        realm.commitTransaction();
        finish();

    }
    public static Intent getLaunchIntent(Uri imageUri, Context context){
        Intent intent = new Intent(context,QuestionActivity.class);
        intent.putExtra(EXTRA_PHOTO_URI,imageUri);
        return intent;
    }
}
