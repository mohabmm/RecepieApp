package com.nocom.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Moha on 11/19/2017.
 */

public class DetaiAdapter extends RecyclerView.Adapter<DetaiAdapter.ForecastAdapterViewHolder>{

    private List<Steps> listItems;
    Context mcontext;


    public DetaiAdapter(Context mcontext, List<Steps> listItems) {
        this.mcontext = mcontext;
        this.listItems = listItems;
    }




    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView shortdes;
        public  TextView describtion;

        public ForecastAdapterViewHolder(View view) {
            super(view);
            shortdes = (TextView) view.findViewById(R.id.shortdescribtion);
            describtion = (TextView) view.findViewById(R.id.describtion);
        }
    }

    @Override
    public DetaiAdapter.ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.deatiladapter;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new DetaiAdapter.ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {

        Steps steps = listItems.get(position);

        holder.shortdes.setText(steps.getNshortDescription());
        holder.describtion.setText(steps.getNdescripsion());

    }

    @Override
    public int getItemCount() {
        if (null == listItems) return 0;
        return listItems.size();
    }
}
