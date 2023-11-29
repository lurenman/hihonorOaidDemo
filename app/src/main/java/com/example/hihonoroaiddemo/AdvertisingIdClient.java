package com.example.hihonoroaiddemo;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AdvertisingIdClient {
    /* loaded from: classes.dex */
    public static final class Info {

        /* renamed from: id */
        public String id;
        public boolean isLimit;
    }

    public static Info getAdvertisingIdInfo(Context context) throws IOException {
        OaidServiceConnection oaidServiceConnection = new OaidServiceConnection();
        oaidServiceConnection.mContext = context;
        if (oaidServiceConnection.isHnOaIdServiceExist(context)) {
            try {
                String oaid_limit_state = Settings.Global.getString(context.getContentResolver(), "oaid_limit_state");
                String oaid = Settings.Global.getString(context.getContentResolver(), "oaid");
                if (!TextUtils.isEmpty(oaid_limit_state) && !TextUtils.isEmpty(oaid)) {
                    boolean limit = Boolean.parseBoolean(oaid_limit_state);
                    Info info = new Info();
                    info.isLimit = limit;
                    info.id = oaid;
                    Log.i("AdvertisingIdPlatform", "getAdvertisingIdInfo cache isLimit=" + limit + "id " + oaid);
                    return info;
                }
            } catch (Throwable th) {
                Log.e("AdvertisingIdPlatform", "getAdvertisingIdInfo cache error=" + th);
            }
            try {
                try {
                    Log.i("AdvertisingIdPlatform", "bindService start");
                    Intent intent = new Intent();
                    intent.setAction("com.hihonor.id.HnOaIdService");
                    intent.setPackage("com.hihonor.id");
                    context.bindService(intent, oaidServiceConnection, Context.BIND_AUTO_CREATE);
                    oaidServiceConnection.countDownLatch.await(2000L, TimeUnit.MILLISECONDS);
                    oaidServiceConnection.unbindService();
                    return oaidServiceConnection.aidInfo;
                } catch (Exception e) {
                    Log.e("AdvertisingIdPlatform", "getAdvertisingIdInfo error=" + e.getMessage());
                    oaidServiceConnection.unbindService();
                    return null;
                }
            } catch (Throwable throwable) {
                oaidServiceConnection.unbindService();
                throw throwable;
            }
        }
        throw new IOException("Service not found or advertisingId not available");
    }

    public static boolean isAdvertisingIdAvailable(Context context) {
        return new OaidServiceConnection().isHnOaIdServiceExist(context);
    }
}
