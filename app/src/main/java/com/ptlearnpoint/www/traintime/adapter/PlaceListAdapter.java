package com.ptlearnpoint.www.traintime.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptlearnpoint.www.traintime.R;
import com.ptlearnpoint.www.traintime.model.Place;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by PT on 1/6/2017.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {

    Context mContext;

    OnItemClickListener mItemClickListener;
    List<Place> dataList = Collections.emptyList()  ;

    // 2
    public PlaceListAdapter(Context context , List<Place> dataList ) {
        this.mContext = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.placeName.setText(dataList.get(position).getFrom()+"-"+dataList.get(position).getTo());
        holder.distance.setText(String.valueOf(dataList.get(position).getDistance())+"KM");
        Picasso.with(mContext).load(dataList.get(position).getPlaceImageId()).into(holder.placeImage);

        Bitmap photo = BitmapFactory.decodeResource(mContext.getResources(), dataList.get(position).getPlaceImageId());

        Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int bgColor = palette.getDominantColor(mContext.getResources().getColor(android.R.color.black));
                holder.placeNameHolder.setBackgroundColor(bgColor);
            }
        });
        animate(holder);
    }
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // 3
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout placeHolder;
        public LinearLayout placeNameHolder;
        public TextView placeName;
        public ImageView placeImage;
        public TextView distance ;

        public ViewHolder(View itemView) {
            super(itemView);
            placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            placeName = (TextView) itemView.findViewById(R.id.placeName);
            placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
            placeImage = (ImageView) itemView.findViewById(R.id.placeImage);
            distance = (TextView) itemView.findViewById(R.id.tv_distance);


            placeHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}