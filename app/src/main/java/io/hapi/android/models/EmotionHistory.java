package io.hapi.android.models;

import java.util.List;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class EmotionHistory {
    private List<Integer> history;

    public EmotionHistory(List<Integer> h)
    {
        history = h;
    }

    public List<Integer> getHistory()
    {
        return history;
    }
}
