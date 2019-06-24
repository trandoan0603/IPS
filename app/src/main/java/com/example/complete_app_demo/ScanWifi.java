package com.example.complete_app_demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ScanWifi {

    Activity contextParent;

    private List<ScanResult> results;
    protected int a;
    protected int b;
    protected int c;




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
                        System.out.print(s1 + "RSSI1: /n" + a);
                    }
                    if (StringUtils.equals(s2, scanResult.SSID)) {
                        b = -scanResult.level;
                        System.out.print(s2 + "RSSI1: /n" + b);
                    }
                    if (StringUtils.equals(s3, scanResult.SSID)) {
                        c = -scanResult.level;
                        System.out.print(s3 + "RSSI1: /n" + c);
                    }
                }
            }
        };
        contextParent.registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        wifiReceiver.onReceive(contextParent, contextParent.getIntent());
        //textView.setText(s1+ " : "+ a+ "/n" + s2 + " : " +b+"/n" + s3 +" : "+ c);
        //Toast.makeText(contextParent, "Scanning Wifi ... ", Toast.LENGTH_SHORT).show();
    }
}