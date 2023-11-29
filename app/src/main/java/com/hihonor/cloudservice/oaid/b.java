package com.hihonor.cloudservice.oaid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.hihonor.cloudservice.oaid.b */
/* loaded from: classes.dex */
public interface b extends IInterface {

    /* renamed from: com.hihonor.cloudservice.oaid.b$a */
    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements b {

        /* renamed from: a */
        public static final /* synthetic */ int f214a = 0;

        /* renamed from: com.hihonor.cloudservice.oaid.b$a$a */
        /* loaded from: classes.dex */
        public static class InternalStub implements b {

            /* renamed from: a */
            public IBinder binder;

            public InternalStub(IBinder iBinder) {
                this.binder = iBinder;
            }

            @Override // com.hihonor.cloudservice.oaid.InterfaceC0890b
            /* renamed from: a */
            public void a(a interfaceC0888a) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.hihonor.cloudservice.oaid.IOAIDService");
                    obtain.writeStrongBinder(interfaceC0888a != null ? (a.Stub) interfaceC0888a : null);
                    if (!this.binder.transact(3, obtain, obtain2, 0)) {
                        int i = Stub.f214a;
                    }
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.binder;
            }

            @Override // com.hihonor.cloudservice.oaid.InterfaceC0890b
            /* renamed from: b */
            public void b(a interfaceC0888a) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.hihonor.cloudservice.oaid.IOAIDService");
                    obtain.writeStrongBinder(interfaceC0888a != null ? (a.Stub) interfaceC0888a : null);
                    if (!this.binder.transact(2, obtain, obtain2, 0)) {
                        int i = Stub.f214a;
                    }
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* renamed from: a */
    void a(a interfaceC0888a) throws RemoteException;

    /* renamed from: b */
    void b(a interfaceC0888a) throws RemoteException;
}