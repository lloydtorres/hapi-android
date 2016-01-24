package io.hapi.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.hapi.android.models.Entry;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class GridImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Entry> entries;

    public GridImageAdapter(Context c, List<Entry> u)
    {
        context = c;
        entries = u;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View image = inflater.inflate(R.layout.grid_image, parent, false);
        RecyclerView.ViewHolder viewHolder = new GridImage(context, image);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GridImage g = (GridImage) holder;
        g.init(entries.get(position));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class GridImage extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Entry entry;
        private Context context;
        private SquareImageView image;

        public GridImage(Context c, View v) {
            super(v);
            context = c;
            image = (SquareImageView) v.findViewById(R.id.grid_image);
            v.setOnClickListener(this);
        }

        public void init(Entry u)
        {
            entry = u;
            image.setImageURI(Uri.parse(entry.getImageUri()));
        }


        @Override
        public void onClick(View v) {
            Intent detailActivityIntent = new Intent(context, DetailActivity.class);
            detailActivityIntent.putExtra("uri", entry.getImageUri());
            detailActivityIntent.putExtra("score", entry.getEmotion().getScore());

            for (int i=0; i<3; i++)
            {
                detailActivityIntent.putExtra("q"+i, entry.getQuestions().get(i).getQuestion());
                detailActivityIntent.putExtra("a"+i, entry.getQuestions().get(i).getResponse());
            }

            context.startActivity(detailActivityIntent);

        }
    }
}
