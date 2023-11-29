package com.example.hihonoroaiddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * sdk连接：https://developer.hihonor.com/cn/kitdoc?category=%E5%9F%BA%E7%A1%80%E6%9C%8D%E5%8A%A1&kitId=11030&navigation=guides&docId=intergrate.md&token=
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity_hihonor_oaid";
    private Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (null != info) {
                                        Log.i(TAG, "getAdvertisingIdInfo id=" + info.id +
                                                ", isLimitAdTrackingEnabled=" + info.isLimit);
                                        Toast.makeText(MainActivity.this, "getAdvertisingIdInfo id=" + info.id+" isLimitAdTrackingEnabled="+info.isLimit, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            Log.i(TAG, "getAdvertisingIdInfo Exception: " + e.toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "getAdvertisingIdInfo Exception:" + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}