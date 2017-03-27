package com.dom.red.model.http.api;

import com.dom.red.model.bean.gank.ClassifyList;
import com.dom.red.model.bean.gank.MeiziBean;
import com.dom.red.model.http.response.GankHttpResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by dom4j on 2017/3/24.
 */

public interface GankApi {

    String Host = "http://gank.io/api/";

    // http://gank.io/api/data/Android/10/1
    @GET("data/{mode}/{num}/{page}")
    Observable<GankHttpResult<List<ClassifyList>>> getClassList(@Path("mode") String mode,@Path("num") int num,@Path("page") int page);

    @GET("data/{mode}/{num}/{page}")
    Observable<GankHttpResult<List<MeiziBean>>> getMeiziList(@Path("mode") String mode, @Path("num") int num, @Path("page") int page);

}
