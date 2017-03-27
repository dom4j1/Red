package com.dom.red.ui.zhihu.activity;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.dom.red.R;
import com.dom.red.base.BaseActivity;
import com.dom.red.model.bean.zhihu.LongCommentBean;
import com.dom.red.model.bean.zhihu.ShortCommentBean;
import com.dom.red.presenter.CommentPresenter;
import com.dom.red.presenter.contract.CommentContract;
import com.dom.red.ui.main.adapter.CommonAdapter;
import com.dom.red.ui.main.adapter.ViewHolder;
import com.dom.red.ui.main.widget.DividerItemDecoration;
import com.dom.red.util.Constants;
import com.dom.red.util.TimeUtil;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dom4j on 2017/3/13.
 */

public class CommentActivity extends BaseActivity<CommentPresenter> implements CommentContract.View, View.OnClickListener {

    @BindView(R.id.tl_comment)
    Toolbar mToolBar;
    @BindView(R.id.tv_num_Lcomment)
    TextView mLongNum;
    @BindView(R.id.rl_long_comment)
    RecyclerView mLongView;
    @BindView(R.id.tv_num_Scomment)
    TextView mShortNum;
    @BindView(R.id.rl_short_comment)
    RecyclerView mShortView;
    @BindView(R.id.il)
    View mIl;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.sl_comment)
    NestedScrollView mScrollView;

    private CommonAdapter mLongAdapter;
    private CommonAdapter mShortAdapter;
    private List<ShortCommentBean.CommentsBean> mShortComments;

    private boolean isFirst = true;

    @Override
    protected int getLayout() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra(Constants.ID, 0);
        int comment = intent.getIntExtra(Constants.NUM, 0);
        setToolBar(mToolBar, comment + "条点评");

        mLongView.setNestedScrollingEnabled(false);
        mLongView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mLongView.setLayoutManager(new LinearLayoutManager(this));

        mShortView.setNestedScrollingEnabled(false);
        mShortView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mShortView.setLayoutManager(new LinearLayoutManager(this));

        mShortNum.setOnClickListener(this);

        initAdapter();
        mPresenter.getLongComment(id);
        mPresenter.getShortComment(id);
    }

    private void initAdapter() {
        mLongAdapter = new CommonAdapter<LongCommentBean.CommentsBean>(this, R.layout.item_long_connment, null) {
            @Override
            public void conver(ViewHolder viewHodler, LongCommentBean.CommentsBean info) {
                viewHodler.loadImageFrescoGif(R.id.sdv_comment_header, info.getAvatar())
                        .setText(R.id.tv_comment_userName, info.getAuthor())
                        .setText(R.id.tv_comment_likeNum, info.getLikes() + "")
                        .setText(R.id.tv_comment_content, info.getContent())
                        .setText(R.id.tv_comment_time, TimeUtil.timeFormat(info.getTime()+""));
            }
        };
        mShortAdapter = new CommonAdapter<ShortCommentBean.CommentsBean>(this, R.layout.item_long_connment, null) {
            @Override
            public void conver(ViewHolder viewHodler, ShortCommentBean.CommentsBean info) {
                viewHodler.loadImageFrescoGif(R.id.sdv_comment_header, info.getAvatar())
                        .setText(R.id.tv_comment_userName, info.getAuthor())
                        .setText(R.id.tv_comment_likeNum, info.getLikes() + "")
                        .setText(R.id.tv_comment_content, info.getContent())
                        .setText(R.id.tv_comment_time,  TimeUtil.timeFormat(info.getTime()+""));
            }
        };
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comment_write, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showLongComment(LongCommentBean longBean) {
        List<LongCommentBean.CommentsBean> comments = longBean.getComments();
        int length = comments.size();
        mLongNum.setText(length + " 条长评");
        if (length > 0) {
            showLongRecyclerView();
            mLongAdapter.setDataList(comments);
            mLongView.setAdapter(mLongAdapter);
        }

    }

    @Override
    public void showShortComment(ShortCommentBean shortBean) {
        mShortComments = shortBean.getComments();
        int length = mShortComments.size();
        mShortNum.setText(length + "条短评");
        mShortAdapter.setDataList(mShortComments);
        mShortView.setAdapter(mShortAdapter);
    }

    private void showShortRecyclerView() {
        mShortView.setVisibility(View.VISIBLE);
    }

    private void showLongRecyclerView() {
        mView.setVisibility(View.GONE);
        mIl.setVisibility(View.GONE);
        mLongView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        showShortRecyclerView();
        if (isFirst) {
            scrollShortComment();
        }
    }

    private void scrollShortComment() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                   while(isFirst && mShortView.getVisibility() == View.VISIBLE){
                       emitter.onNext(new Object());
                       isFirst=false;
                   }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object Object) throws Exception {
                        if(mIl.getVisibility() == View.VISIBLE){
                            mScrollView.scrollBy(0,getScrollViewHeight()-getItemHeight());
                        }else{
                            mScrollView.scrollBy(0,mLongView.getMeasuredHeight()+getItemHeight());
                        }
                    }
                });
    }

    public int getScrollViewHeight(){
        return mScrollView.getMeasuredHeight();
    }

    public int getItemHeight(){
        return mShortNum.getMeasuredHeight();
    }

}
