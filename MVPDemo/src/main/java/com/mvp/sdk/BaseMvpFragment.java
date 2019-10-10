package com.mvp.sdk;

import android.content.Context;
import android.support.v4.app.Fragment;

/**Fragment基类，具体作用和BaseMvpActivity相同
 * Created by win on 2019/10/10.
 */

public abstract class BaseMvpFragment<M extends InterfaceModel,V extends InterfaceView,P extends BasePresenter<M,V>>
        extends Fragment
        implements InterfaceMvp<M,V,P>{
    protected P mPresenter;

    protected boolean isBindPresenter(){
        return (null != mPresenter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mPresenter = createPresenter();
        if(isBindPresenter()){
            this.mPresenter.registerModel(createModel());
            this.mPresenter.registerView(createView());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(isBindPresenter()){
            mPresenter.destroy();
        }
    }
}
