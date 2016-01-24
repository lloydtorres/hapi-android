package io.hapi.android;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import io.hapi.android.models.Entry;
import io.hapi.android.models.Question;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class ReportActivity extends AppCompatActivity {

    private List<Entry> entries;
    private List<Integer> scores;
    private List<Question> positives;
    private List<Question> negatives;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.report_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        updateBullshit();
        scores = new ArrayList<Integer>();
        positives = new ArrayList<Question>();
        negatives = new ArrayList<Question>();
        for (Entry e : entries)
        {
            int score = e.getEmotion().getScore();
            scores.add(score);

            if (score < 50)
            {
                negatives.addAll(e.getQuestions());
            }
            else
            {
                positives.addAll(e.getQuestions());
            }
        }

        viewPager = (ViewPager) findViewById(R.id.report_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.report_tabs);
        tabLayout.setupWithViewPager(viewPager);
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

    public void updateBullshit()
    {
        entries = retrieveEntries();
    }

    public List<Entry> retrieveEntries()
    {
        Realm realm = Realm.getInstance(this);
        RealmResults<Entry> res = realm.where(Entry.class).findAll();
        return res;
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        String[] titles = { "Stats", "Plus", "Minus" };


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    StatsFragment tab1 = new StatsFragment();
                    tab1.setScores(scores);
                    return tab1;
                case 1:
                    StoryFragment tab2 = new StoryFragment();
                    tab2.setTitle("What lifts you up?");
                    tab2.setQuestions(positives);
                    return tab2;
                case 2:
                    StoryFragment tab3 = new StoryFragment();
                    tab3.setTitle("What brings you down?");
                    tab3.setQuestions(negatives);
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
