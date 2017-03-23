package com.dom.red.model.http.help;

import android.util.Log;
import android.widget.Toast;

import com.dom.red.app.App;
import com.dom.red.model.bean.DetailBean;
import com.dom.red.model.bean.ExtraInfo;
import com.dom.red.model.bean.HomeListBean;
import com.dom.red.model.bean.LongCommentBean;
import com.dom.red.model.bean.ShortCommentBean;
import com.dom.red.model.bean.ThemeList;
import com.dom.red.model.bean.ThemeListBean;
import com.dom.red.model.http.api.ZhiHuApi;
import com.dom.red.util.Final;
import com.dom.red.util.LogUtil;
import com.dom.red.util.NewWorkUtil;
import com.dom.red.util.SpUtil;
import com.dom.red.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.BuildConfig;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dom4j on 2017/3/7.
 */

public class RetrofitHelper {
    private static OkHttpClient mOkHttpClient = null;
    private static ZhiHuApi mZhiHuApi = null;

    public RetrofitHelper(){
        init();
    }

    private void init(){
        initOkHttp();
        mZhiHuApi = getApiService(ZhiHuApi.HOST,ZhiHuApi.class);
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
        builder.connectTimeout(20, TimeUnit.SECONDS);
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

}
