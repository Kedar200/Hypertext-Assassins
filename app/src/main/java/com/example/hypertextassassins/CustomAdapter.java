package com.example.hypertextassassins;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<String> mData;

    private List<String> mData2;
    private List<String> mData3;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    CustomAdapter(Context context, List<String> data,List<String> data2,List<String> data3) {

        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mData2=data2;
        this.mData3=data3;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String var1 = getItem(mData,position);
        String var2 = getItem(mData2,position);
        String var3 = getItem(mData3,position);
        Log.d("Hello",mData.get(position)+mData2.get(position)+mData3.get(position));
        holder.type.setText(var1);
        holder.date.setText(var2);
        holder.dec.setText(var3);


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView type;
        TextView date;
        TextView dec;
        ViewHolder(View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.Type);
            date=itemView.findViewById(R.id.Date);
            dec=itemView.findViewById(R.id.desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(List<String> data, int id) {
        return data.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}