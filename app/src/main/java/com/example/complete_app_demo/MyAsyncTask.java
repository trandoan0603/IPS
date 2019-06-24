package com.example.complete_app_demo;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.SystemClock;
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
    int a =0;
    int b =0;
    int c =0;
    int n =0;
    int m =0;




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
                    SystemClock.sleep(500);
                    getData.getData(scanWifi.getA(), scanWifi.getB(), scanWifi.getC());
                    a = scanWifi.getA();
                    b = scanWifi.getB();
                    c = scanWifi.getC();
                    publishProgress(getData.getPos());
                    SystemClock.sleep(4500);
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
                if(m == 0){
                    Animation animation = new AlphaAnimation(1,0);
                    animation.setDuration(1000);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.REVERSE);
                    ImageView pointView = (ImageView) contextParent.findViewById(R.id.pointImg);
                    pointView.setX(450);
                    pointView.setY(100);
                    pointView.setVisibility(View.VISIBLE);
                    pointView.setAnimation(animation);
                    TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
                    textView.setText("Result="+getData.getPos());
                }
                else if(m==1){
                    Animation animation = new AlphaAnimation(1,0);
                    animation.setDuration(1000);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.REVERSE);
                    ImageView pointView = (ImageView) contextParent.findViewById(R.id.pointImg);
                    pointView.setX(450);
                    pointView.setY(300);
                    pointView.setVisibility(View.VISIBLE);
                    TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
                    textView.setText("Result="+getData.getPos());
                }
                else if(m==2){
                    Animation animation = new AlphaAnimation(1,0);
                    animation.setDuration(1000);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.REVERSE);
                    ImageView pointView = (ImageView) contextParent.findViewById(R.id.pointImg);
                    pointView.setX(400);
                    pointView.setY(600);
                    pointView.setVisibility(View.VISIBLE);
                    TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
                    textView.setText("Result="+getData.getPos());
                }
                else if(m==3){
                    Animation animation = new AlphaAnimation(1,0);
                    animation.setDuration(1000);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.REVERSE);
                    ImageView pointView = (ImageView) contextParent.findViewById(R.id.pointImg);
                    pointView.setX(600);
                    pointView.setY(600);
                    pointView.setVisibility(View.VISIBLE);
                    TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
                    textView.setText("Result="+getData.getPos());
                }
                else if(m==4){
                    Animation animation = new AlphaAnimation(1,0);
                    animation.setDuration(1000);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.REVERSE);
                    ImageView pointView = (ImageView) contextParent.findViewById(R.id.pointImg);
                    pointView.setX(380);
                    pointView.setY(900);
                    pointView.setVisibility(View.VISIBLE);
                    TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
                    textView.setText("Result="+getData.getPos());
                }
                else {
                    Animation animation = new AlphaAnimation(1,0);
                    animation.setDuration(1000);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.REVERSE);
                    ImageView pointView = (ImageView) contextParent.findViewById(R.id.pointImg);
                    pointView.setX(450);
                    pointView.setY(1050);
                    pointView.setVisibility(View.VISIBLE);
                    TextView textView = (TextView) contextParent.findViewById(R.id.resultTxt);
                    textView.setText("Result="+getData.getPos());
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