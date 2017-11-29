package com.nocom.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.adapterViewHolder>  {
    static private ListItemClickListener mOnClickListener;


    Context mContext;
    ArrayList<Recipe> recipes;


    public MainAdapter(ListItemClickListener mOnClickListener, ArrayList<Recipe> items, Context context) {
        this.mOnClickListener = mOnClickListener;
        mContext = context;
        recipes = items;
    }

    @Override
    public adapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.main_adapter;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new adapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(adapterViewHolder holder, final int position) {

        final Recipe currentRecipie = recipes.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putParcelableArrayList("Movie",currentRecipie.ningriedients);
                b.putParcelableArrayList("moha",currentRecipie.getNsteps());
                Intent startChildActivityIntent = new Intent(mContext, DetailActivity.class);
                startChildActivityIntent.putExtras(b);
                startChildActivityIntent.putExtra(Intent.EXTRA_TEXT,currentRecipie.getNingriedients());
                startChildActivityIntent.putExtra(Intent.EXTRA_REFERRER, currentRecipie.getNsteps());

                Log.i(" adapter",currentRecipie.getNingriedients().toString());
                startChildActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(startChildActivityIntent);




            }
        });

        if (recipes != null && recipes.size() > 0) {
            int  id = currentRecipie.getNid();
            switch (id) {

                case 1:

                    if (recipes.get(position).getPoster1().isEmpty()) {
                        holder.image.setImageResource(R.drawable.nutella);
                    } else {

                        Picasso
                                .with(holder.image.getContext())
                                .load(recipes.get(position).getPoster1())
                                .placeholder(R.drawable.nutella)
                                .resize(6000, 2000)
                                .onlyScaleDown()
                                .into(holder.image);
                    }

                    break;

                case 2:

                    if (recipes.get(position).getPoster1().isEmpty()) {
                        holder.image.setImageResource(R.drawable.brownies);
                    } else {

                        Picasso
                                .with(holder.image.getContext())
                                .load(recipes.get(position).getPoster1())
                                .placeholder(R.drawable.brownies)
                                .into(holder.image);
                    }
                    break;

                case 3:

                    if (recipes.get(position).getPoster1().isEmpty()) {
                        holder.image.setImageResource(R.drawable.yellowcake);
                    } else {

                        Picasso
                                .with(holder.image.getContext())
                                .load(recipes.get(position).getPoster1())
                                .placeholder(R.drawable.yellowcake)
                                .into(holder.image);
                    }
                    break;

                case 4:

                    if (recipes.get(position).getPoster1().isEmpty()) {
                        holder.image.setImageResource(R.drawable.cheeseh);
                    } else {
                        Picasso
                                .with(holder.image.getContext())
                                .load(recipes.get(position).getPoster1())
                                .placeholder(R.drawable.cheeseh)
                                .into(holder.image);
                        break;
                    }
            }
        }
    }

    @Override
    public int getItemCount() {

        if (null == recipes) return 0;
        return recipes.size();

    }

    public void setWeatherData(ArrayList<Recipe> weatherData) {
        recipes = weatherData;
        notifyDataSetChanged();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    public  class adapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        public adapterViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }


    }
}
