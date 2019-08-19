package com.semisky.autotesttool.pattern_p;

import java.lang.ref.WeakReference;

/**
 * Created by win on 2019/8/18.
 */

public abstract class BasePresenter<V> {
    protected WeakReference<V> mViewRer;

    public void attachView(V view){
        this.mViewRer = new WeakReference<V>(view);
    }

    public void detachView(){
        if(isBindView()){
            this.mViewRer.clear();
            this.mViewRer = null;
        }
    }

    public boolean isBindView(){
        return (null != mViewRer && null != mViewRer.get());
    }
}
