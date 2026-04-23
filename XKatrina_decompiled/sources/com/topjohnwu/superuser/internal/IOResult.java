package com.topjohnwu.superuser.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class IOResult implements Parcelable {
    private static final String REMOTE_ERR_MSG = "Exception thrown on remote process";
    private final Object val;
    private static final ClassLoader cl = IOResult.class.getClassLoader();
    static final Parcelable.Creator<IOResult> CREATOR = new Parcelable.Creator<IOResult>() { // from class: com.topjohnwu.superuser.internal.IOResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IOResult createFromParcel(Parcel parcel) {
            return new IOResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IOResult[] newArray(int i) {
            return new IOResult[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IOResult() {
        this.val = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IOResult(Object obj) {
        this.val = obj;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.val);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkException() throws IOException {
        if (this.val instanceof Throwable) {
            throw new IOException(REMOTE_ERR_MSG, (Throwable) this.val);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> T tryAndGet() throws IOException {
        checkException();
        return (T) this.val;
    }

    private IOResult(Parcel parcel) {
        this.val = parcel.readValue(cl);
    }
}
