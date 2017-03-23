package com.dom.red.model.http.api;

import com.dom.red.model.bean.LongCommentBean;
import com.dom.red.model.bean.DetailBean;
import com.dom.red.model.bean.ExtraInfo;
import com.dom.red.model.bean.HomeListBean;
import com.dom.red.model.bean.ShortCommentBean;
import com.dom.red.model.bean.ThemeList;
import com.dom.red.model.bean.ThemeListBean;
import com.dom.red.model.bean.WelcomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dom4j on 2017/3/7.
 */

public interface ZhiHuApi {
     String HOST = "http://news-at.zhihu.com/api/4/";

     //http://news-at.zhihu.com/api/4/start-image/1080*1776
     @GET("start-image/{res}")
     Observable<WelcomeBean> getWelComeImage(@Path("res") String res);

    //http://news-at.zhihu.com/api/4/themes   主题列表
    @GET("themes")
    Observable<ThemeListBean> getThemeList();

    //http://news-at.zhihu.com/api/4/news/latest  主页最新数据
    @GET("news/latest")
    Observable<HomeListBean> getHomeList();

    //http://news-at.zhihu.com/api/4/news/before/20131119
    @GET("news/before/{date}")
    Observable<HomeListBean> getBeforeList(@Path("date") String date);

    //http://news-at.zhihu.com/api/4/news/3892357
    @GET("news/{id}")
    Observable<DetailBean> getContent(@Path("id") int id);

    //http://news-at.zhihu.com/api/4/story-extra/#{id} 额外信息
    @GET("story-extra/{id}")
    Observable<ExtraInfo>  getNewsInfo(@Path("id") int id);

    //http://news-at.zhihu.com/api/4/story/#{id}/long-comments
    @GET("story/{id}/long-comments")
    Observable<LongCommentBean> getLongComment(@Path("id") int id);

    //http://news-at.zhihu.com/api/4/story/#{id}/short-comments
    @GET("story/{id}/short-comments")
    Observable<ShortCommentBean> getShortComment(@Path("id") int id);

    //http://news-at.zhihu.com/api/4/theme/11
    @GET("theme/{id}")
    Observable<ThemeList> getThemeList(@Path("id") int id);
}
