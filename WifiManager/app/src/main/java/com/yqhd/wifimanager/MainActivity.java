package com.yqhd.wifimanager;

import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private WifiAdmins mWifiAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWifi();
        findViewById(R.id.btn_connect_wifi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // connect();
            }
        });
    }

    private void initWifi() {
        mWifiAdmin = new WifiAdmins(this);
    }

    public void checkNetCardState() {
        mWifiAdmin.checkState();
    }

    public void connect() {
        mWifiAdmin.openWifi();
        // 已经开启Wifi的时候,返回的是3
        // 如果还没有开启wifi的时候,返回的是2
        new Thread(new Runnable() {
            @Override
            public void run() {
                // WifiManager.WIFI_STATE_ENABLING 表示正在开启WIFI
                while (WifiManager.WIFI_STATE_ENABLED != mWifiAdmin.checkState()){
                    Log.d(LOG_TAG, "connect");
                }
                // 连接当前wifi可用
                mWifiAdmin.addNetwork(mWifiAdmin.createWifiInfo("7seven-portal", "together", 3));
            }
        }).start();
        // startActivityForResult(new Intent(
        // android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
        // startActivity(new
        // Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
    }
}
