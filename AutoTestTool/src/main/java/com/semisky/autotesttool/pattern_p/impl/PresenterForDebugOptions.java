package com.semisky.autotesttool.pattern_p.impl;

import com.semisky.autotesttool.R;
import com.semisky.autotesttool.app.AutoTestApp;
import com.semisky.autotesttool.pattern_p.BasePresenter;
import com.semisky.autotesttool.pattern_p.IPresenterForDebugOptions;
import com.semisky.autotesttool.pattern_v.IFragmentForDebugOptions;
import com.semisky.autotesttool.utils.Logutil;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class PresenterForDebugOptions<V extends IFragmentForDebugOptions>
        extends BasePresenter<V>
        implements IPresenterForDebugOptions {
    private static final String TAG = PresenterForDebugOptions.class.getSimpleName();


    @Override
    public void reqShowList() {
        if (!isBindView()) {
            return;
        }
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                Logutil.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
                // 获取xml里自动化测试目录内容
                String[] autoTedtMenuOptionsContent = AutoTestApp
                        .getInstance()
                        .getResources()
                        .getStringArray(R.array.auto_test_menu_options);
                emitter.onNext(Arrays.asList(autoTedtMenuOptionsContent));
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> datas) throws Exception {
                        Logutil.e(TAG, "After observeOn(mainThread)，Current thread is " + Thread.currentThread().getName());
                        mViewRer.get().onShowList(datas);
                    }
                });


    }
}
