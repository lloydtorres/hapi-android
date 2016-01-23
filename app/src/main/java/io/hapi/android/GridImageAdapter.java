package io.hapi.android;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Lloyd on 2016-01-23.
 */
public class GridImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Uri> mImages;

    public GridImageAdapter(List<Uri> u)
    {
        mImages = u;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View image = inflater.inflate(R.layout.grid_image, parent, false);
        RecyclerView.ViewHolder viewHolder = new GridImage(image);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GridImage g = (GridImage) holder;
        g.init(mImages.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class GridImage extends RecyclerView.ViewHolder {

        private SquareImageView image;

        public GridImage(View v) {
            super(v);
            image = (SquareImageView) v.findViewById(R.id.grid_image);
        }

        public void init(Uri u)
        {
            image.setImageURI(u);
        }
    }
}
