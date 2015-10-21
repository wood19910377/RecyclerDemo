package com.jash.recyclerdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jash
 * Date: 15-10-20
 * Time: 下午4:53
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<String> list;
    private OnChildClickListener listener;
    private RecyclerView recyclerView;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setListener(OnChildClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        view.setBackgroundColor(Color.RED);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        if (listener != null) {
            listener.onChildClick(position);
        }
    }
    public void remove(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }
    public void add(int position, String data){
        list.add(position, data);
        notifyItemInserted(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    public interface OnChildClickListener{
        void onChildClick(int position);
    }
}
