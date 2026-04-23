package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import com.topjohnwu.superuser.Shell;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ResultImpl extends Shell.Result {
    static ResultImpl INSTANCE = new ResultImpl();
    static ResultImpl SHELL_ERR = new ResultImpl();
    int code = -1;
    List<String> err;
    List<String> out;

    @Override // com.topjohnwu.superuser.Shell.Result
    @NonNull
    public List<String> getOut() {
        List<String> list = this.out;
        return list == null ? Collections.emptyList() : list;
    }

    @Override // com.topjohnwu.superuser.Shell.Result
    @NonNull
    public List<String> getErr() {
        List<String> list = this.err;
        return list == null ? Collections.emptyList() : list;
    }

    @Override // com.topjohnwu.superuser.Shell.Result
    public int getCode() {
        return this.code;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void callback(Executor executor, final Shell.ResultCallback resultCallback) {
        if (resultCallback != null) {
            if (executor == null) {
                resultCallback.onResult(this);
            } else {
                executor.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.ResultImpl$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ResultImpl.this.m136lambda$callback$0$comtopjohnwusuperuserinternalResultImpl(resultCallback);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$callback$0$com-topjohnwu-superuser-internal-ResultImpl  reason: not valid java name */
    public /* synthetic */ void m136lambda$callback$0$comtopjohnwusuperuserinternalResultImpl(Shell.ResultCallback resultCallback) {
        resultCallback.onResult(this);
    }
}
