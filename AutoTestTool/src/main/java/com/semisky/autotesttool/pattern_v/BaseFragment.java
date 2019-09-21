package com.semisky.autotesttool.pattern_v;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.semisky.autotesttool.pattern_p.BasePresenter;

/**
 * Created by win on 2019/8/18.
 */

public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment {
    protected View mContentView;
    protected P mPresenter;

    protected abstract void initListeners();
    protected abstract void initViews();
    protected abstract P createPresenter();
    protected abstract int getLayoutResID();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.mContentView = LayoutInflater.from(getActivity()).inflate(getLayoutResID(),container,false);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
        initViews();
        initListeners();
    }


    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }


}
