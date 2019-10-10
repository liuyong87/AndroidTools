package com.mvp.sdk;

/**
 * Presenter层接口
 * 作用：程序中的逻辑超类，负责处理具体事务
 * Created by win on 2019/10/10.
 */

public interface InterfacePresenter<M extends InterfaceModel,V extends InterfaceView> {

    /**
     * 注册模型层
     * @param model
     */
    void registerModel(M model);

    /**
     * 注册视图层
     * @param view
     */
    void registerView(V view);

    /**
     * 获取视图层
     */
    V getView();



    /**
     * 销毁动作（如：Activity,Fragment与表示层Presenter解绑）
     */
    void destroy();
}
