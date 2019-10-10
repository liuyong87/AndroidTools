package com.mvp.sdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Activity基类，具体的实现Model、View的绑定，我们自己的Activity可直接继承于此类或者自行实现BaseActivity继承于此类
 * Created by win on 2019/10/10.
 */

public abstract class BaseMvpActivity<M extends InterfaceModel,V extends InterfaceView,P extends BasePresenter<M,V>>
        extends FragmentActivity
        implements InterfaceMvp<M,V,P>{

    protected P mPresenter;

    protected boolean isBindPresenter(){
        return (null != mPresenter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPresenter = createPresenter();
        if(isBindPresenter()){
            // 将Model层注册到Presenter层
            this.mPresenter.registerModel(createModel());
            // 将View层注册到Presenter层
            this.mPresenter.registerView(createView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBindPresenter()){
            // View层与Presenter层解绑
            mPresenter.destroy();
        }
    }
}
