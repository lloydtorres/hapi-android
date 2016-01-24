package io.hapi.android;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class DetailActivity extends AppCompatActivity {
    private ImageView mainPic;
    private TextView time;
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


        mainPic = (ImageView) findViewById(R.id.detail_picture);
        time = (TextView) findViewById(R.id.detail_date);
        score = (TextView) findViewById(R.id.detail_score);

        s1Question = (TextView) findViewById(R.id.story_question_1);
        s1Response = (TextView) findViewById(R.id.story_response_1);

        s2Question = (TextView) findViewById(R.id.story_question_2);
        s2Response = (TextView) findViewById(R.id.story_response_2);

        s3Question = (TextView) findViewById(R.id.story_question_3);
        s3Response = (TextView) findViewById(R.id.story_response_3);

        Uri imgUri = Uri.parse("android.resource://io.hapi.android/" + R.drawable.nic_cage);
        mainPic.setImageURI(imgUri);

        PrettyTime prettyTime = new PrettyTime();
        time.setText(String.format("Posted %s", prettyTime.format(new Date())));
        score.setText(String.valueOf(92));

        s1Question.setText("Whatever1q");
        s1Response.setText("Whatever1a");

        s2Question.setText("Whatever2q");
        s2Response.setText("Whatever2a");

        s3Question.setText("Whatever3q");
        s3Response.setText("Whatever3a");
    }
}
