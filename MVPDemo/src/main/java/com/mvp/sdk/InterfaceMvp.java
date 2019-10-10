package com.mvp.sdk;

/**
 * InterfaceMvp是用来创建Model、View和Presenter层的，我们的MVP框架只去调用它们，具体的实现由真正的View层来做
 * Created by win on 2019/10/10.
 */

public interface InterfaceMvp<M extends InterfaceModel,V extends InterfaceView,P extends BasePresenter<M,V>> {

    M createModel();

    V createView();

    P createPresenter();
}
