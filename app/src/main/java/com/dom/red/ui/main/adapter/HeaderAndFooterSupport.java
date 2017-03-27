package com.dom.red.ui.main.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dom4j on 2017/3/8.
 *
 *    装饰着模式实现 RecyclerView 添加头布局/尾部局支持的类
 */

public class HeaderAndFooterSupport extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 记录被增强之前的Adapter
     */
    private final RecyclerView.Adapter mAdapter;

    /**
     * 记录头布局View的集合
     */
    private SparseArray<View> mHeaders = new SparseArray<>();
    /**
     * 记录尾部局View的集合
     */
    private SparseArray<View> mFooters = new SparseArray<>();

    /**
     * 头布局的标记
     */
    private static final int HEADER_TYPE_ITEM = 10000;

    /**
     * 尾部局的标记
     */
    private static final int FOOTER_TYPE_ITEM = 20000;

    /**
     * 添加头布局的方法
     */
    public void addHeaderView(View view){
        mHeaders.put(mHeaders.size()+HEADER_TYPE_ITEM,view);
    }

    /**
     * 添加尾部局的方法
     */
    public void addFooterView(View view){
        mFooters.put(mFooters.size()+FOOTER_TYPE_ITEM,view);
    }

    /**
     * 是否是头布局
     * @param position 当前position/需要判断的position
     * @return
     */
    public boolean isHederViewPos(int position){
        return position < getHeadersCount();
    }

    /**
     * 是否是尾布局
     * @param position 当前position/需要判断的position
     * @return
     */
    public boolean isFooterViewPos(int position){
        return position >= getHeadersCount() + getRealCount();
    }

    /**
     *  返回头布局的数量
     * @return
     */
    public int getHeadersCount(){
        return mHeaders.size();
    }

    /**
     * 返回尾部局的数量
     * @return
     */
    public int getFootersCount(){
        return mFooters.size();
    }

    /**
     * 返回未添加头/尾布局之前的Item数量
     * @return
     */
    public int getRealCount(){
        return mAdapter.getItemCount();
    }

    /**
     * 构造方法 需要传入将要被增强的Adapter
     * @param adapter
     */
    public HeaderAndFooterSupport(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果当前的条目类型是头布局类型 就将此头布局View取出构建一个单独的ViewHolder并返回
        if(mHeaders.get(viewType) != null){
            ViewHolder viewHolder = ViewHolder.createViewHolder(parent.getContext(),mHeaders.get(viewType));
            return viewHolder;
        }
        if(mFooters.get(viewType) != null){
            ViewHolder viewHolder = ViewHolder.createViewHolder(parent.getContext(),mFooters.get(viewType));
            return viewHolder;
        }

        return mAdapter.createViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /**
         * 如果是头布局或者尾布局不用处理直接返回
         */
        if(isHederViewPos(position)){
            return;
        }
        if(isFooterViewPos(position)){
            return;
        }

        mAdapter.onBindViewHolder(holder,position - getHeadersCount());
    }

    /**
     *  添加头尾布局的核心方法
     *     因为RecyclerView的头/尾布局都可以被看做是一个Item,只是他们分别在RecyclerView的头部与尾部,我们视同多条目加载的思想实现
     *     首先判断当前的position下的item是否是头布局/尾部局 如果是 就将当前position下的Key(相对应的头布局集合/尾部局集合)作为ItemType返回
     */
    @Override
    public int getItemViewType(int position) {
        if(isHederViewPos(position)){
            return mHeaders.keyAt(position);
        }
        if(isFooterViewPos(position)){
            return mFooters.keyAt(position - getHeadersCount() - getRealCount());
        }
        return mAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + mHeaders.size() + mFooters.size();
    }

    /**
     *   处理GridlayoutManager下的头/尾布局
     *   因为我们的处理方式 是使用的多条目加载 所以GridLayoutManager下 他们只会作为一个Item处理
     *   我们需要将头布局/尾部局沾满我们所设置的"跨度"
     *    思路:
     *   通过 GridLayoutManager的setSpanSizeLookUp方法为Item设置所占用的"跨度"
     *    判断 如果是头/尾布局的Item 就将当前的Item的跨度设置为全部的"跨度"
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    if(mHeaders.get(type) != null){
                        return gridLayoutManager.getSpanCount();
                    }
                    if(mFooters.get(type) != null){
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    /**
     *  瀑布流下 同理
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {

        int position = holder.getLayoutPosition();
        if(isHederViewPos(position) || isFooterViewPos(position)){
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if(layoutParams != null &&
                    layoutParams instanceof StaggeredGridLayoutManager.LayoutParams){
                StaggeredGridLayoutManager.LayoutParams p =
                        (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                p.setFullSpan(true);
            }
        }
    }

    public void cleanView() {
        if(mHeaders != null){
            mHeaders.clear();
        }
        if(mFooters != null){
            mFooters.clear();
        }
    }

}
