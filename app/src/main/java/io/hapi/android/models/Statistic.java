package io.hapi.android.models;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class Statistic {
    private String description;
    private int value;

    public Statistic(String d, int v)
    {
        description = d;
        value = v;
    }

    public String getDescription()
    {
        return description;
    }

    public int getValue()
    {
        return value;
    }
}
