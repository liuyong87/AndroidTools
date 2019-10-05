package com.semisky.autotesttool.pattern_v.impl;

import com.semisky.autotesttool.R;
import com.semisky.autotesttool.app.AutoTestApp;
import com.semisky.autotesttool.pattern_p.impl.PresenterForDebugOptions;
import com.semisky.autotesttool.pattern_v.BaseFragment;
import com.semisky.autotesttool.pattern_v.IFragmentForDebugOptions;

import java.util.List;

/**
 * 调试选项选项主菜单
 */
public class FragmentForDebugOptions extends BaseFragment<IFragmentForDebugOptions, PresenterForDebugOptions<IFragmentForDebugOptions>> implements IFragmentForDebugOptions {
    private static final String TAG = FragmentForDebugOptions.class.getSimpleName();


    @Override
    public void onShowList(List<String> datas) {

    }

    @Override
    protected void initData() {
        if (!isBindPresenter()) {
            return;
        }
        mPresenter.reqShowList();
    }

    @Override
    protected void initListeners() {
        AutoTestApp.getInstance().getResources().getStringArray(R.array.auto_test_menu_options);
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected PresenterForDebugOptions createPresenter() {
        return new PresenterForDebugOptions();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_debug_options;
    }

}
