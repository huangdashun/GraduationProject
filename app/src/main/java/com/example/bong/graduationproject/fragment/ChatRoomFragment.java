package com.example.bong.graduationproject.fragment;

import android.view.View;

import com.example.bong.graduationproject.R;
import com.example.bong.graduationproject.base.BaseFragment;

/**
 * Created by hs on 2017/3/5.
 * 聊天室
 */

public class ChatRoomFragment extends BaseFragment {
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_chat_room, null);
        return view;
    }
}
