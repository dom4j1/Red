package com.dom.red.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dom.red.R;
import com.dom.red.util.PxUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by 姜鹏 on 2017/1/2.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    /**
     * 上下文
     */
    private final Context mContext;
    /**
     * 使用集合来存储item上的控件
     */
    private SparseArray<View> mViewList;

    /**
     * 加载item的布局
     */
    private View mConvertView;

    /**
     * 当前ViewHolder所关联的Adapter
     */
    private CommonAdapter mAdapter;

    private ViewHolder(Context context,View itemView) {
        super(itemView);
        this.mContext = context;
        this.mConvertView = itemView;
        mViewList = new SparseArray<View>();
    }

    /**
     *
     * 获取ViewHolder的方法
     *
     * @param context 上下文
     * @param layoutID 布局ID
     * @param parent 父控件
     * @return 返回当前的布局ID所对应的ViewHolder的实例
     */
    public static ViewHolder getViewHolder(Context context, int layoutID, ViewGroup parent){
        //将布局ID转化为视图
        View itemView = LayoutInflater.from(context).inflate(layoutID,parent,false);
        //实例化当前ViewHolder
        ViewHolder viewHolder = new ViewHolder(context,itemView);
        //返回
        return viewHolder;
    }

    public static  ViewHolder createViewHolder(Context context,View itemView){
        ViewHolder viewHolder = new ViewHolder(context,itemView);
        return viewHolder;
    }

    /**
     *
     * 通过ID取控件的方法 对ItemView的重用已经进行了缓存
     *
     * @param viewID 控件的ID
     * @return 返回id所对应的控件
     */
    public <T extends View>T getView(int viewID){
        //从集合中取控件
        View view = mViewList.get(viewID);
        //如果没有就通过findViewById创建一个,并缓存到集合中
        if(view == null){
            view = mConvertView.findViewById(viewID);
            mViewList.put(viewID,view);
        }
        return (T) view;
    }

    /**
     * 辅助方法
     */
    public ViewHolder setText(int viewID,String string){
        TextView textView = getView(viewID);
        textView.setText(string);
        return this;
    }

    public ViewHolder setimageResouse(int viewID,int resID){
        ImageView imageView = getView(viewID);
        imageView.setImageResource(resID);
        return this;
    }

    public ViewHolder setBackgroundColor(int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolder setTextColor(int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes)
    {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    public ViewHolder setOnClickLisenter(int viewID,View.OnClickListener listener){
        View view = getView(viewID);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }


    public ViewHolder loadImageFrescoGif(int id, String url) {
        SimpleDraweeView imageView = getView(id);
        String format = url.substring(url.length()-1,url.length());
        if(format.equals("f")){
            DraweeController controller =(PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(url))
                    .setAutoPlayAnimations(true) //自动播放gif动画
                    .build();
            imageView.setController(controller);
        }else{
            imageView.setImageURI(url);
        }

        return this;
    }

    public ViewHolder loadImageFresco(int id, String url){
        SimpleDraweeView imageView = getView(id);
        imageView.setImageURI(url);
        return this;
    }

    public ViewHolder setOnItemClickLisenter(final OnItemClickLisenter item){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ImageView iv = getView(R.id.item_home_sdv);
                item.itemClick(iv,ViewHolder.super.getLayoutPosition());
            }
        });
        return this;
    }

    public ViewHolder setOnItemClickLisenter(final OnItemClickLisenter item, final int viewID){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView iv = getView(viewID);
                item.itemClick(iv,ViewHolder.super.getLayoutPosition());
            }
        });
        return this;
    }

    public ViewHolder loadImageGlide(int id, String url) {
        ImageView imageView = getView(id);
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.rotate_pro)
                .error(R.mipmap.load_error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(imageView);

        return this;
    }

    public ViewHolder loadImageGlideNoScroll(int id, String url) {
        ImageView imageView = getView(id);
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.rotate_pro)
                .error(R.mipmap.load_error)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade()
                .into(imageView);
        return this;
    }


    public ViewHolder loadImageGlideScroll(int id, String url){
        ImageView imageView = getView(id);
        //Glide在加载GridView等时,由于ImageView和Bitmap实际大小不符合,第一次时加载可能会变形(我这里出现了放大),必须在加载前再次固定ImageView大小
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        int width = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        lp.width = width/2;
        float dimension = mContext.getResources().getDimension(R.dimen.y150);
        lp.height = (int)dimension;
        Glide.with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.load_error)
                .crossFade()
                .into(imageView);
        return this;
    }

    public interface OnItemClickLisenter{
        void itemClick(View startView, int position);
    }
}
