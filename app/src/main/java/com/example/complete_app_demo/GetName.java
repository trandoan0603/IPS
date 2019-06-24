package com.example.complete_app_demo;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetName {

    private String data;
    private String s1 ;
    private String s2 ;
    private String s3;
    private static final String TAG = "GetName";

    public String getS1(){
        return s1;
    }
    public String getS2(){
        return s2;
    }
    public String getS3(){
        return s3;
    }
    public String getData(){
        return data;
    }

    public void getName(){
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://192.168.1.66:5000/hello")
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    data = response.body().string().replace("\"","");
                    Log.d(TAG,"DATA = " + data);
                    s1 = data.split(",")[0];
                    s2 = data.split(",")[1];
                    s3 = data.split(",")[2].replace("\n","");
                }
            }
        });
    }

}
