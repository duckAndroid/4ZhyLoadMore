package com.pythoncat.zhyloadmore.recycler.service;

import com.pythoncat.zhyloadmore.recycler.domain.Bean;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pythonCat on 2016/7/8.
 */
public class LoadApi {


    public static Observable<List<Bean>> refreshApi() {
        return LoadService.timer3s().flatMap(t -> Observable.just(LoadService.refresh()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<List<Bean>> loadMoreApi() {
        return LoadService.timer3s().flatMap(t -> Observable.just(LoadService.loadMore()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
