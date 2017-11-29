
package com.nocom.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * {@link IngriAdapter} exposes a list of weather forecasts to a
 * {@link android.support.v7.widget.RecyclerView}
 */
public class IngriAdapter extends RecyclerView.Adapter<IngriAdapter.ForecastAdapterViewHolder> {


   private List<Ingrideint> listItems;
    Context mcontext;


    public IngriAdapter(Context mcontext, List<Ingrideint> listItems) {
        this.mcontext = mcontext;
        this.listItems = listItems;
    }




    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {

        public  TextView quantity;
        public  TextView measure;
        public  TextView name;
        public   TextView mquantity;
        public   TextView mmeasure;
        public   TextView mname;

        public ForecastAdapterViewHolder(View view) {
            super(view);
            quantity = (TextView) view.findViewById(R.id.quantity);
            measure = (TextView) view.findViewById(R.id.measure);
            name = (TextView) view.findViewById(R.id.name);
            mquantity=(TextView)view.findViewById(R.id.Quantity);
            mmeasure=(TextView)view.findViewById(R.id.Measure);
            mname=(TextView)view.findViewById(R.id.Name);
        }
    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingradapter;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new ForecastAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, final int position) {


        Ingrideint ingrideint = listItems.get(position);
        holder.name.setText(ingrideint.getNname());
        holder.quantity.setText(String.valueOf(ingrideint.getNquantity()));
        holder.measure.setText(ingrideint.getNmeasure());
        holder.mname.setText("Name");
        holder.mmeasure.setText("Measure");
        holder.mquantity.setText("Quantity");



    }

        @Override
    public int getItemCount() {
        if (null == listItems) return 0;
            Log.i("listItems.size()", String.valueOf(listItems.size()));
        return listItems.size();

    }




}