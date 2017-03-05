package com.example.bong.graduationproject.fragment;

import android.view.View;

import com.example.bong.graduationproject.R;
import com.example.bong.graduationproject.base.BaseFragment;

/**
 * Created by hs on 2017/3/5.
 * 通讯录
 */

public class AddressBookFragment extends BaseFragment {
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_address_book, null);
        return view;
    }
}
