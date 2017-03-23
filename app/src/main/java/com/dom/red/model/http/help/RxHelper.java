package com.dom.red.model.http.help;


import com.dom.red.util.NewWorkUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dom4j on 2017/3/7.
 */

public class RxHelper {


    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
   public static <T> ObservableTransformer rxSchedulerHelper(){
       return new ObservableTransformer<T,T>() {
           @Override
           public ObservableSource<T> apply(Observable<T> upstream) {
               return upstream.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread());
           }
       };
   }


    /**
     * 生成Observable
     * @param t
     * @param <T>
     * @return
     */
     public static <T> Observable<T> createData(final T t){

         return Observable.create(new ObservableOnSubscribe<T>() {
             @Override
             public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                 try{
                     emitter.onNext(t);
                     emitter.onComplete();
                 }catch(Exception e){
                     emitter.onError(e);
                 }
             }
         });
     }

}
