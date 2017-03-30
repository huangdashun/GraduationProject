package com.it.dashunzi.graduationproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it.dashunzi.graduationproject.R;
import com.it.dashunzi.graduationproject.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by hs on 2017/3/5.
 * 设置
 */

public class ChatSettingFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_chat_setting, null);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
