package com.dom.red.model.http.help;

import android.util.Log;

import com.dom.red.app.App;
import com.dom.red.model.bean.zhihu.HotListBean;
import com.dom.red.model.bean.zhihu.SectionChildListBean;
import com.dom.red.model.bean.gank.ClassifyList;
import com.dom.red.model.bean.gank.MeiziBean;
import com.dom.red.model.bean.zhihu.DetailBean;
import com.dom.red.model.bean.zhihu.ExtraInfo;
import com.dom.red.model.bean.zhihu.HomeListBean;
import com.dom.red.model.bean.zhihu.LongCommentBean;
import com.dom.red.model.bean.zhihu.SectionBean;
import com.dom.red.model.bean.zhihu.ShortCommentBean;
import com.dom.red.model.bean.zhihu.ThemeList;
import com.dom.red.model.bean.zhihu.ThemeListBean;
import com.dom.red.model.http.api.GankApi;
import com.dom.red.model.http.api.ZhiHuApi;
import com.dom.red.model.http.response.GankHttpResult;
import com.dom.red.util.NewWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dom4j on 2017/3/7.
 */

public class RetrofitHelper {
    private static OkHttpClient mOkHttpClient = null;
    private static ZhiHuApi mZhiHuApi = null;
    private static GankApi mGankApi = null;

    public RetrofitHelper(){
        init();
    }

    private void init(){
        initOkHttp();
        mZhiHuApi = getApiService(ZhiHuApi.HOST,ZhiHuApi.class);
        mGankApi = getApiService(GankApi.Host,GankApi.class);
    }

    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         * 日志拦截器
         */
        Interceptor loggingIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                long t1 = System.nanoTime();
                Log.e("TAG",String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Log.e("TAG",String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                return response;
            }
        };
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = null;
                if(!NewWorkUtil.isNetworkConnected()){
                  Request request = chain.request()
                          .newBuilder()
                          .cacheControl(CacheControl.FORCE_CACHE)
                          .build();
                      response = chain.proceed(request);
                    return response;
                }else{
                    int maxAge = 0;
                    response = chain.proceed(chain.request())
                            .newBuilder()
                            .removeHeader("pragma")
                            .header("Cache-Control","max-age=" + maxAge)
                            .build();
                    return response;
                }
            }
        };
        /**
         * 缓存路径
         */
        File httpCacheDirectory = new File(App.getInstance().getExternalFilesDir("responses").getPath());
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        builder.addInterceptor(loggingIntercept);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(20,TimeUnit.SECONDS);
        builder.writeTimeout(20,TimeUnit.SECONDS);
        //错误重连
        mOkHttpClient = builder.build();
    }

    private <T> T getApiService(String host, Class<T> clz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(clz);
    }

    public Observable<ThemeListBean> getThemeList() { return mZhiHuApi.getThemeList();}

    public Observable<HomeListBean> getHomeList() { return mZhiHuApi.getHomeList();}

    public Observable<HomeListBean> getBeforeList(String date) { return mZhiHuApi.getBeforeList(date);}

    public Observable<DetailBean> getDetailList(int id) {
        return mZhiHuApi.getContent(id);
    }

    public Observable<ExtraInfo> getNewsInfo(int id){return mZhiHuApi.getNewsInfo(id); }

    public Observable<LongCommentBean> getLongComment(int id){return mZhiHuApi.getLongComment(id); }

    public Observable<ShortCommentBean> getShortComment(int id){return mZhiHuApi.getShortComment(id); }

    public Observable<ThemeList> getThemeList(int id){return mZhiHuApi.getThemeList(id); }

    public Observable<GankHttpResult<List<ClassifyList>>> getClassList(String mode, int num, int page){return mGankApi.getClassList(mode,num,page);}

    public Observable<GankHttpResult<List<MeiziBean>>> getMeiziList(String mode, int num, int page){return mGankApi.getMeiziList(mode,num,page);}

    public Observable<SectionBean> getSectionList(){return mZhiHuApi.getSectionList();}

    public Observable<SectionChildListBean> getSectionChildList(int id){return mZhiHuApi.getSectionChildList(id);}

    public Observable<HotListBean> getHotList(){return mZhiHuApi.getHotList();}

}
