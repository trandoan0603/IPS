package com.example.complete_app_demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ScanWifi {

    Activity contextParent;

    private List<ScanResult> results;
    private int a;
    private int b;
    private int c;
    private static final String TAG = "ScanWifi";



    public ScanWifi(Activity contextParent) {
        this.contextParent = contextParent;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public void scanWifi(WifiManager wifiManager, String s1, String s2, String s3) {

        BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                results = wifiManager.getScanResults();
                contextParent.unregisterReceiver(this);
                for (ScanResult scanResult : results) {
                    if (StringUtils.equals(s1, scanResult.SSID)) {
                        a = -scanResult.level;
                        Log.d(TAG,s1 + "RSSI1: /n" + a);
                    }
                    if (StringUtils.equals(s2, scanResult.SSID)) {
                        b = -scanResult.level;
                        Log.d(TAG,s2 + "RSSI1: /n" + b);
                    }
                    if (StringUtils.equals(s3, scanResult.SSID)) {
                        c = -scanResult.level;
                        Log.d(TAG,s3 + "RSSI1: /n" + c);
                    }
                }
            }
        };
        contextParent.registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        wifiReceiver.onReceive(contextParent, contextParent.getIntent());
    }
}