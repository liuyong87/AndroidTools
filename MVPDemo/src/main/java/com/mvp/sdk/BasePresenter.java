package com.mvp.sdk;

import java.lang.ref.WeakReference;

/**
 * Presenter抽象类<br>
 * 作用：所有Presenter层的抽象类，负责Model、View层的引用和销毁<br>
 * 说明：<br>
 * BasePresenter是我们要直接继承使用的Presenter层父类，
 * 它实现了Presenter接口中的抽象方法，并且为了防止内存泄漏，
 * 我们View层的引用要使用弱引用。在MVP模式中，内存泄漏的主要
 * 原因是由于当前View层（如Activity或者Fragment）在卸载时，
 * Model层中仍有业务没有结束（如子线程未完成、网络请求超时等），
 * 而这里的Presenter层中拥有Mode层和View层的引用，所以Presenter
 * 层也暂时无法释放，最终导致View的引用也没有释放，我们的Activity
 * 或者Fragment就算时销毁了，GC也无法回收它们，因为还有引用在指向它们呢。
 * 我们也不必非要使用弱引用来维护View层，其实在View层卸载时，
 * 只要主动让指向View的引用为空，也可以让Activity或者Fragment顺利回收，
 * 而且在View卸载时我们也可以选择是否停止当前Model层的业务，在BasePresenter类中，
 * 我们也同样实现了这个逻辑，就是destroy()方法，它通过调用onViewDestroy()
 * 来让具体实现这个方法的类来完成相应的业务逻辑。
 */

public abstract class BasePresenter<M extends InterfaceModel,V extends InterfaceView> implements InterfacePresenter<M,V> {
    private WeakReference<V> mViewRfr;
    protected M mModelRfr;

    @Override
    public void registerModel(M model) {
        this.mModelRfr = model;
    }

    @Override
    public void registerView(V view) {
        if(null == mViewRfr){
            this.mViewRfr = new WeakReference<V>(view);
        }
    }

    @Override
    public V getView() {
        if(isBindView()){
            return mViewRfr.get();
        }
        return null;
    }

    protected boolean isBindView() {
        return (null != mViewRfr && null != mViewRfr.get());
    }

    @Override
    public void destroy() {
        if(null != mViewRfr){
            mViewRfr.clear();
            mViewRfr = null;
        }
        onViewDestroy();
    }

    /**
     * 与视图层解绑
     */
    protected abstract void onViewDestroy();
}
