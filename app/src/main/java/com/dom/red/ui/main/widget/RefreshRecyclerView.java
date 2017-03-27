package com.dom.red.ui.main.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.dom.red.R;

/**
 * Created by dom4j on 2017/3/8.
 */

public class RefreshRecyclerView extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mSrl;
    private RecyclerView mRl;
    private LinearLayoutManager linearLayoutManager;
    private OnRefresh2Lisenter mLisenter;
    private int lastCount;
    private boolean mFalg = true;


    public RefreshRecyclerView(Context context) {
        this(context,null);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs ,0);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.view_refreshview, this);
        mSrl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        mRl = (RecyclerView) view.findViewById(R.id.rl);
        linearLayoutManager = new LinearLayoutManager(getContext());
        mRl.setLayoutManager(linearLayoutManager);

        mSrl.setOnRefreshListener(this);
        mRl.addOnScrollListener(new OnScrollListener());

    }

    public void setLayoutManager(RecyclerView.LayoutManager manager){
        mRl.setLayoutManager(manager);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        mRl.setAdapter(adapter);
    }

    public void setOnRefreshLisenter(OnRefresh2Lisenter lisenter){
        this.mLisenter = lisenter;
    }

    public void setOnRefreshLisenter(OnRefresh2Lisenter lisenter,boolean isTrue){
        this.mLisenter = lisenter;
        this.mFalg = isTrue;
    }

    @Override
    public void onRefresh() {
         if(mLisenter != null){
             mLisenter.refresh();
         }
    }

    public void setRefreshIng(boolean refreshIng) {
        mSrl.setRefreshing(refreshIng);
    }

    public interface OnRefresh2Lisenter{
        void loadMore();

        void refresh();
    }

    public class OnScrollListener extends RecyclerView.OnScrollListener {
        public void onScrolled(RecyclerView recyclerView, int dx, int dy){
            if(dy > 0){
                int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
                int count = linearLayoutManager.getItemCount();
                if(mFalg){
                    if(lastPosition + 2 == count && lastPosition + 2 != lastCount){
                        lastCount = count;
                        if(mLisenter != null){
                            mLisenter.loadMore();
                        }
                    }
                }else{
                    if(lastPosition + 2 == count){
                        lastCount = count;
                        if(mLisenter != null){
                            mLisenter.loadMore();
                        }
                    }
                }
            }
        }
    }

    public RecyclerView getRecyclerView(){
        return mRl;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSrl;
    }
}
