package com.zhiying.zylibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZY
 * @date 2018/4/19
 */
public abstract class ZYCommonAdapter<T> extends RecyclerView.Adapter<ZYViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;


    public ZYCommonAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    public List<T> getmDatas() {
        return mDatas == null ? new ArrayList<T>() : mDatas;
    }

    public void setmDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ZYViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ZYViewHolder viewHolder = ZYViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ZYViewHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDatas == null || mDatas.size() == 0 ? 0 : mDatas.size();
    }

    public abstract void convert(ZYViewHolder holder, T t, int position);
}