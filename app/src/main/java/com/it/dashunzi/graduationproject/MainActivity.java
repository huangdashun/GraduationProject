package com.it.dashunzi.graduationproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.it.dashunzi.graduationproject.base.BaseFragment;
import com.it.dashunzi.graduationproject.fragment.AddressBookFragment;
import com.it.dashunzi.graduationproject.fragment.ChatRecordFragment;
import com.it.dashunzi.graduationproject.fragment.ChatRoomFragment;
import com.it.dashunzi.graduationproject.fragment.ChatSettingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    private RadioGroup mRgMain;
    private List<BaseFragment> mBaseFragment;//存放Fragment的集合
    private int position;//记录选中的Fragment的对应的位置
    private Fragment mCurrentFragment;//当前的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //初始化监听器
        initListener();

    }

    private void initView() {

        setContentView(R.layout.activity_main);
        //初始化radiobutton
        mRgMain = (RadioGroup) findViewById(R.id.rg_main);
    }


    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new ChatRoomFragment());//聊天室
        mBaseFragment.add(new AddressBookFragment());//通讯录
        mBaseFragment.add(new ChatRecordFragment());//聊天记录
        mBaseFragment.add(new ChatSettingFragment());//设置页面
    }

    private void initListener() {
        mRgMain.setOnCheckedChangeListener(this);
        //设置默认选中聊天室
        mRgMain.check(R.id.rb_chat_room);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_chat_room://聊天室
                position = 0;
                break;
            case R.id.rb_address_book://通讯录
                position = 1;
                break;
            case R.id.rb_record://聊天记录
                position = 2;
                break;
            case R.id.rb_setting://设置页面
                position = 3;
                break;
        }
        //根据位置得到对应的Fragment
        BaseFragment toFragment = getFragment();
        //去替换
        switchFragment(mCurrentFragment, toFragment);
    }

    /**
     * @param fromFragment 之前显示的Fragment,即将被隐藏
     * @param toFragment   马上要切换到的Fragment,即将要显示
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (fromFragment != toFragment) {//避免重复点击某一个RadioButton
            mCurrentFragment = toFragment;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //开始切换
            if (!toFragment.isAdded()) {//如果toFragment没有被添加
                if (fromFragment != null) {//隐藏fromFragment
                    ft.hide(fromFragment);
                }
                if (toFragment != null) {//添加
                    ft.add(R.id.fl_content, toFragment).commit();
                }
            } else {
                //toFragment已经被添加
                if (fromFragment != null) {
                    ft.hide(fromFragment);
                }
                //显示toFragment
                if (toFragment != null) {
                    ft.show(toFragment).commit();
                }
            }
        }
    }

    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

}
