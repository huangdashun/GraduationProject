package com.it.dashunzi.graduationproject.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by hs on 2017/3/5.
 * 基类,公共类
 */

public abstract class BaseFragment extends Fragment {
    //上下文
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当孩子需要初始化数据,或者联网请求绑定数据,展示数据的时候 可以重新改方法
     */
    private void initData() {

    }


}
