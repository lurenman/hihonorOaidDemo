package com.example.hihonoroaiddemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;

import com.hihonor.cloudservice.oaid.a;
import com.hihonor.cloudservice.oaid.b;

import java.util.concurrent.CountDownLatch;

public class OaidServiceConnection implements ServiceConnection {

    /* renamed from: a */
    public AdvertisingIdClient.Info aidInfo;

    /* renamed from: b */
    public Context mContext;

    /* renamed from: c */
    public ABinderStubProxy aBinderStubProxy = new ABinderStubProxy();

    /* renamed from: d */
    public BBinderStubProxy bBinderStubProxy = new BBinderStubProxy();

    /* renamed from: e */
    public CountDownLatch countDownLatch = new CountDownLatch(2);

    /* renamed from: com.hihonor.ads.identifier.a$a */
    /* loaded from: classes.dex */
    public class ABinderStubProxy extends a.Stub {

        public ABinderStubProxy() {
        }

        @Override // com.hihonor.cloudservice.oaid.InterfaceC0888a
        /* renamed from: a */
        public void a(int i, long j, boolean z, float f, double d, String str) {
        }

        @Override // com.hihonor.cloudservice.oaid.InterfaceC0888a
        /* renamed from: a */
        public void a(int i, Bundle bundle) {
            Log.e("AdvertisingIdPlatform", "OAIDCallBack handleResult retCode=" + i + " retInfo=" + bundle);
            if (i != 0 || bundle == null) {
                Log.e("AdvertisingIdPlatform", "OAIDCallBack handleResult error retCode=$ " + i);
            } else if (OaidServiceConnection.this.aidInfo != null) {
                OaidServiceConnection.this.aidInfo.id = bundle.getString("oa_id_flag");
                Log.i("AdvertisingIdPlatform", "OAIDCallBack handleResult success");
            }
            OaidServiceConnection.this.countDownLatch.countDown();
        }
    }

    /* renamed from: com.hihonor.ads.identifier.a$b */
    /* loaded from: classes.dex */
    public class BBinderStubProxy extends a.Stub {
        public BBinderStubProxy() {
        }

        @Override // com.hihonor.cloudservice.oaid.InterfaceC0888a
        /* renamed from: a */
        public void a(int i, long j, boolean z, float f, double d, String str) {
        }

        @Override // com.hihonor.cloudservice.oaid.InterfaceC0888a
        /* renamed from: a */
        public void a(int i, Bundle bundle) {
            Log.e("AdvertisingIdPlatform", "OAIDCallBack handleResult retCode=" + i + " retInfo= " + bundle);
            if (i != 0 || bundle == null) {
                Log.e("AdvertisingIdPlatform", "OAIDLimitCallback handleResult error retCode= " + i);
            } else if (OaidServiceConnection.this.aidInfo != null) {
                boolean z = bundle.getBoolean("oa_id_limit_state");
                OaidServiceConnection.this.aidInfo.isLimit = z;
                Log.i("AdvertisingIdPlatform", "OAIDLimitCallback handleResult success  isLimit=" + z);
            }
            OaidServiceConnection.this.countDownLatch.countDown();
        }
    }

    /* renamed from: a */
    public final void unbindService() {
        Log.i("AdvertisingIdPlatform", "disconnect");
        try {
            this.mContext.unbindService(this);
        } catch (Exception e) {
            Log.e("AdvertisingIdPlatform", "OAIDClientImpl#disconnect#Disconnect error::" + e.getMessage());
        }
    }

    /* renamed from: a */
    public boolean isHnOaIdServiceExist(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            packageManager.getPackageInfo("com.hihonor.id", 0);
            Intent intent = new Intent("com.hihonor.id.HnOaIdService");
            intent.setPackage("com.hihonor.id");
            return !packageManager.queryIntentServices(intent, 0).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        b bIInterface;
        Log.i("AdvertisingIdPlatform", "onServiceConnected ");
        try {
            this.aidInfo = new AdvertisingIdClient.Info();
            int i = b.Stub.f214a;
            if (iBinder == null) {
                bIInterface = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.hihonor.cloudservice.oaid.IOAIDService");
                bIInterface = (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new b.Stub.InternalStub(iBinder) : (b) queryLocalInterface;
            }
            bIInterface.b(this.aBinderStubProxy);
            bIInterface.a(this.bBinderStubProxy);
        } catch (Exception e) {
            Log.e("AdvertisingIdPlatform", "onServiceConnected error:" + e.getMessage());
            this.countDownLatch.countDown();
            this.countDownLatch.countDown();
            unbindService();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i("AdvertisingIdPlatform", "onServiceDisconnected ");
        this.countDownLatch.countDown();
        this.countDownLatch.countDown();
    }
}
