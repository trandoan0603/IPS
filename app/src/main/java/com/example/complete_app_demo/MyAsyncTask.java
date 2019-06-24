package com.example.complete_app_demo;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

    Activity contextParent;
    WifiManager wifiManager;
    private int a =0;
    private int b =0;
    private int c =0;
    private int n =0;
    private int m =0;



    private static final String TAG = "MyAsyncTask";
    public MyAsyncTask (Activity contextParent){
        this.contextParent = contextParent;
    }

    GetName getName = new GetName();
    GetData getData = new GetData();

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        wifiManager = (WifiManager) contextParent.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifiManager.isWifiEnabled()){
            Toast.makeText(contextParent,"Wifi is disabled... We need to enable it", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }
        Toast.makeText(contextParent,"Start Positioning",Toast.LENGTH_SHORT).show();
        ImageView houseView = (ImageView) contextParent.findViewById(R.id.houseImg);
        houseView.setVisibility(View.VISIBLE);
    }
    @Override
    protected Void doInBackground(Void... voids) {
        ScanWifi scanWifi = new ScanWifi(contextParent);
        while (getName.getData()==null){
            getName.getName();
            m = m+1;
            SystemClock.sleep(1000);
            if(m==2){
                break;
            }
        }
        if(getName.getData()!=null) {
            while (a == 0 || b == 0 || c == 0) {
                scanWifi.scanWifi(wifiManager, getName.getS1(), getName.getS2(), getName.getS3());
                n = n + 1;
                SystemClock.sleep(1000);
                if (n == 3) {
                    a = scanWifi.getA();
                    b = scanWifi.getB();
                    c = scanWifi.getC();
                    break;
                }
            }
            if (a != 0 && b != 0 && c != 0) {
                while (true) {
                    if (isCancelled()) {
                        break;
                    }
                    scanWifi.scanWifi(wifiManager, getName.getS1(), getName.getS2(), getName.getS3());
                    SystemClock.sleep(1000);
                    getData.getData(scanWifi.getA(), scanWifi.getB(), scanWifi.getC());
                    a = scanWifi.getA();
                    b = scanWifi.getB();
                    c = scanWifi.getC();
                    publishProgress(getData.getPos());
                    SystemClock.sleep(2000);
                }
            } else {
                publishProgress(getData.getPos());
            }
        }else{
            publishProgress(getData.getPos());
        }

        return null;
    }
    protected void onProgressUpdate(Integer...pos){
        super.onProgressUpdate(pos);
        if(getName.getData()!=null) {
            if (pos != null) {
                int m = pos[0];
                Animation animation = new AlphaAnimation(1,0);
                animation.setDuration(1000);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(Animation.INFINITE);
                animation.setRepeatMode(Animation.REVERSE);
                ImageView pointView = (ImageView) contextParent.findViewById(R.id.pointImg);
                pointView.setVisibility(View.VISIBLE);
                pointView.setAnimation(animation);
                TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
                Log.d(TAG, "Result= "+ getData.getPos());
                textView.setText("Press 'Back' to return to Menu");
                if(m == 0){
                    pointView.setX(470);
                    pointView.setY(140);
                }
                else if(m==1){
                    pointView.setX(470);
                    pointView.setY(400);
                }
                else if(m==2){
                    pointView.setX(370);
                    pointView.setY(770);

                }
                else if(m==3){
                    pointView.setX(630);
                    pointView.setY(770);
                }
                else if(m==4){
                    pointView.setX(470);
                    pointView.setY(1090);
                }
                else {
                    pointView.setX(470);
                    pointView.setY(1270);
                }
            }
        }
    }
    protected void onPostExecute(final Void unused){
        super.onPreExecute();
        if(getName.getData()==null){
            TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
            textView.setText("Cannot connect to server");
        }
        else {
            if(getData.getResult()==null){
                TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
                textView.setText("Cannot find required Wifi: "+getName.getData());
            }
        }
    }

}