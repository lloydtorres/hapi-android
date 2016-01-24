package io.hapi.android;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class DetailActivity extends AppCompatActivity {
    private ImageView mainPic;
    private TextView score;

    private TextView s1Question;
    private TextView s1Response;

    private TextView s2Question;
    private TextView s2Response;

    private TextView s3Question;
    private TextView s3Response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Uri uri = Uri.parse(getIntent().getStringExtra("uri"));
        int scoreVal = getIntent().getIntExtra("score", 0);

        List<String> qs = new ArrayList<String>();
        List<String> rs = new ArrayList<String>();

        for (int i=0; i<3; i++)
        {
            qs.add(getIntent().getStringExtra("q"+i));
            rs.add(getIntent().getStringExtra("a"+i));
        }

        mainPic = (ImageView) findViewById(R.id.detail_picture);
        score = (TextView) findViewById(R.id.detail_score);

        s1Question = (TextView) findViewById(R.id.story_question_1);
        s1Response = (TextView) findViewById(R.id.story_response_1);

        s2Question = (TextView) findViewById(R.id.story_question_2);
        s2Response = (TextView) findViewById(R.id.story_response_2);

        s3Question = (TextView) findViewById(R.id.story_question_3);
        s3Response = (TextView) findViewById(R.id.story_response_3);

        mainPic.setImageURI(uri);
        score.setText(String.valueOf(scoreVal));

        s1Question.setText(qs.get(0));
        s1Response.setText(rs.get(0));

        s2Question.setText(qs.get(1));
        s2Response.setText(rs.get(1));

        s3Question.setText(qs.get(2));
        s3Response.setText(rs.get(2));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Respond to the action bar's Up/Home button
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
