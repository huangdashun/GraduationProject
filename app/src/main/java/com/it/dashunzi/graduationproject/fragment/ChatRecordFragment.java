package com.it.dashunzi.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it.dashunzi.graduationproject.R;
import com.it.dashunzi.graduationproject.base.BaseFragment;

/**
 * Created by hs on 2017/3/30.
 * 聊天记录
 */

public class ChatRecordFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_chat_record, null);
        return view;
    }
}
