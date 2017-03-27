package com.dom.red.model.http.api;

import com.dom.red.model.bean.zhihu.HotListBean;
import com.dom.red.model.bean.zhihu.SectionChildListBean;
import com.dom.red.model.bean.zhihu.LongCommentBean;
import com.dom.red.model.bean.zhihu.DetailBean;
import com.dom.red.model.bean.zhihu.ExtraInfo;
import com.dom.red.model.bean.zhihu.HomeListBean;
import com.dom.red.model.bean.zhihu.SectionBean;
import com.dom.red.model.bean.zhihu.ShortCommentBean;
import com.dom.red.model.bean.zhihu.ThemeList;
import com.dom.red.model.bean.zhihu.ThemeListBean;
import com.dom.red.model.bean.zhihu.WelcomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dom4j on 2017/3/7.
 */

public interface ZhiHuApi {
    String HOST = "http://news-at.zhihu.com/api/";

    //http://news-at.zhihu.com/api/4/start-image/1080*1776
    @GET("4/start-image/{res}")
    Observable<WelcomeBean> getWelComeImage(@Path("res") String res);

    //http://news-at.zhihu.com/api/4/themes   主题列表
    @GET("4/themes")
    Observable<ThemeListBean> getThemeList();

    //http://news-at.zhihu.com/api/4/news/latest  主页最新数据
    @GET("4/news/latest")
    Observable<HomeListBean> getHomeList();

    //http://news-at.zhihu.com/api/4/news/before/20131119
    @GET("4/news/before/{date}")
    Observable<HomeListBean> getBeforeList(@Path("date") String date);

    //http://news-at.zhihu.com/api/4/news/3892357
    @GET("4/news/{id}")
    Observable<DetailBean> getContent(@Path("id") int id);

    //http://news-at.zhihu.com/api/4/story-extra/#{id} 额外信息
    @GET("4/story-extra/{id}")
    Observable<ExtraInfo>  getNewsInfo(@Path("id") int id);

    //http://news-at.zhihu.com/api/4/story/#{id}/long-comments
    @GET("4/story/{id}/long-comments")
    Observable<LongCommentBean> getLongComment(@Path("id") int id);

    //http://news-at.zhihu.com/api/4/story/#{id}/short-comments
    @GET("4/story/{id}/short-comments")
    Observable<ShortCommentBean> getShortComment(@Path("id") int id);

    //http://news-at.zhihu.com/api/4/theme/11
    @GET("4/theme/{id}")
    Observable<ThemeList> getThemeList(@Path("id") int id);

    //http://news-at.zhihu.com/api/3/sections 专栏信息
    @GET("3/sections")
    Observable<SectionBean> getSectionList();

    //http://news-at.zhihu.com/api/3/section/1
    @GET("3/section/{id}")
    Observable<SectionChildListBean> getSectionChildList(@Path("id") int id);

    //http://news-at.zhihu.com/api/3/news/hot
    @GET("3/news/hot")
    Observable<HotListBean> getHotList();
}
