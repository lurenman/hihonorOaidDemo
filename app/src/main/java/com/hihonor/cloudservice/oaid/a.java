package com.hihonor.cloudservice.oaid;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.hihonor.cloudservice.oaid.a */
/* loaded from: classes.dex */
public interface a extends IInterface {

    /* renamed from: com.hihonor.cloudservice.oaid.a$a */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements a {
        public Stub() {
            attachInterface(this, "com.hihonor.cloudservice.oaid.IOAIDCallBack");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                a(data.readInt(), data.readLong(), data.readInt() != 0, data.readFloat(), data.readDouble(), data.readString());
            } else if (code != 2) {
                if (code != 1598968902) {
                    return super.onTransact(code, data, reply, flags);
                }
                reply.writeString("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                return true;
            } else {
                data.enforceInterface("com.hihonor.cloudservice.oaid.IOAIDCallBack");
                a(data.readInt(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
            }
            reply.writeNoException();
            return true;
        }
    }

    /* renamed from: a */
    void a(int i, long j, boolean z, float f, double d, String str);

    /* renamed from: a */
    void a(int i, Bundle bundle);

}