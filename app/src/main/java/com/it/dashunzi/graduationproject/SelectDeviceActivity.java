package com.it.dashunzi.graduationproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.it.dashunzi.graduationproject.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SelectDeviceActivity extends AppCompatActivity {
    private static final String TAG = "SelectDeviceActivity";
    private static final int REQUEST_ENABLE_CODE = 0x1003;
    @InjectView(R.id.tv_state)
    TextView mTvState;
    @InjectView(R.id.btn_scan_dev)
    Button mBtnScanDev;
    @InjectView(R.id.lv_scan_dev)
    ListView mLvScanDev;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> mArrayList = new ArrayList<String>();
    private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();//存放设备的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);
        ButterKnife.inject(this);

        initView();
        initListener();
        //打开并查找蓝牙设备
        openAndFindBTDevice();//打开并查找蓝牙设备

        //用来接收到设备查找到的广播和扫描完成的广播
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        //动态注册广播接收器
        registerReceiver(mReceiver, filter);
    }


    /**
     * 初始化view
     */
    private void initView() {
        //请求显示进度条(以后再做)
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrayList);
        mLvScanDev.setAdapter(mAdapter);
    }

    /**
     * 初始化listener
     */
    private void initListener() {
        mLvScanDev.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BluetoothDevice targetDev = mDeviceList.get(position);
                Intent intent = new Intent();
                intent.putExtra("DEVICE", targetDev);
                //当用户点击某设备时,将该设备对象返回给调用者
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mBtnScanDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBluetoothAdapter.isDiscovering()) {
                    mBluetoothAdapter.startDiscovery();
                }
            }
        });
    }

    /**
     * 打开并查找蓝牙设备
     */
    private void openAndFindBTDevice() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            ToastUtil.show(this, R.string.device_no_bluetooth);
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_CODE && resultCode == RESULT_OK) {
            Log.i(TAG, "设备打开成功");
            findBTDevice();
        } else {
            Log.i(TAG, "设备打开失败");
        }
    }

    /**
     * 查询蓝牙设备
     */
    private void findBTDevice() {
        //用来保存已经配对的蓝牙设备对象
        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
        if (bondedDevices.size() > 0) {
            for (BluetoothDevice device : bondedDevices) {
                //将已经配对设备信息添加到ListView中
                mArrayList.add(device.getName() + "\n" + device.getAddress());
                mDeviceList.add(device);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //扫描到新的蓝牙设备
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //获得蓝牙设备对象
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (mDeviceList.contains(device)) {
                    return;
                }
                mArrayList.add(device.getName() + "\n" + device.getAddress());
                mDeviceList.add(device);
                mAdapter.notifyDataSetChanged();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                //扫描完成,关闭显示,可以做一些操作
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
