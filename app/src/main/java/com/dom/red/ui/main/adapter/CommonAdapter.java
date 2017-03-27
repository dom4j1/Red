package com.dom.red.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by dom4j on 2017/3/8.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 布局id
     */
    protected int mLayoutID;

    /**
     * 数据集合
     */
    protected List<T> mData;

    /**
     * 当前Adapter所关联的Adapter
     */
    private ViewHolder mViewHolder;


    public CommonAdapter(Context context, int layoutID,List<T> data){
        this.mData = data;
        this.mContext = context;
        this.mLayoutID = layoutID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化ViewHolder
        mViewHolder = ViewHolder.getViewHolder(mContext,mLayoutID,parent);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //具体赋值逻辑留给用户处理
        conver(holder,mData.get(position));
    }

    public abstract void conver(ViewHolder viewHodler,T o);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setDataList(List<T> list){
           this.mData = list;
           this.notifyDataSetChanged();
    }

}
