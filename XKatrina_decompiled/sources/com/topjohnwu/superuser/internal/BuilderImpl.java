package com.topjohnwu.superuser.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.topjohnwu.superuser.NoShellException;
import com.topjohnwu.superuser.Shell;
import java.io.IOException;
import java.lang.reflect.Constructor;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class BuilderImpl extends Shell.Builder {
    private static final String TAG = "BUILDER";
    private Shell.Initializer[] initializers;
    long timeout = 20;
    private int flags = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasFlags(int i) {
        return (this.flags & i) == i;
    }

    @Override // com.topjohnwu.superuser.Shell.Builder
    @NonNull
    public Shell.Builder setFlags(int i) {
        this.flags = i;
        return this;
    }

    @Override // com.topjohnwu.superuser.Shell.Builder
    @NonNull
    public Shell.Builder setTimeout(long j) {
        this.timeout = j;
        return this;
    }

    public void setInitializersImpl(Class<? extends Shell.Initializer>[] clsArr) {
        this.initializers = new Shell.Initializer[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            try {
                Constructor<? extends Shell.Initializer> declaredConstructor = clsArr[i].getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                this.initializers[i] = declaredConstructor.newInstance(new Object[0]);
            } catch (ClassCastException | ReflectiveOperationException e) {
                Utils.err(e);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0022, code lost:
        if (r1.isRoot() == false) goto L32;
     */
    @Override // com.topjohnwu.superuser.Shell.Builder
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.topjohnwu.superuser.internal.ShellImpl build() {
        /*
            r6 = this;
            r0 = 1
            boolean r1 = r6.hasFlags(r0)
            java.lang.String r2 = "su"
            r3 = 0
            r4 = 0
            if (r1 != 0) goto L28
            r1 = 2
            boolean r5 = r6.hasFlags(r1)
            if (r5 == 0) goto L28
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch: com.topjohnwu.superuser.NoShellException -> L27
            r1[r4] = r2     // Catch: com.topjohnwu.superuser.NoShellException -> L27
            java.lang.String r5 = "--mount-master"
            r1[r0] = r5     // Catch: com.topjohnwu.superuser.NoShellException -> L27
            com.topjohnwu.superuser.internal.ShellImpl r1 = r6.build(r1)     // Catch: com.topjohnwu.superuser.NoShellException -> L27
            boolean r5 = r1.isRoot()     // Catch: com.topjohnwu.superuser.NoShellException -> L25
            if (r5 != 0) goto L29
            goto L28
        L25:
            goto L29
        L27:
        L28:
            r1 = r3
        L29:
            if (r1 != 0) goto L44
            boolean r5 = r6.hasFlags(r0)
            if (r5 != 0) goto L44
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch: com.topjohnwu.superuser.NoShellException -> L43
            r5[r4] = r2     // Catch: com.topjohnwu.superuser.NoShellException -> L43
            com.topjohnwu.superuser.internal.ShellImpl r1 = r6.build(r5)     // Catch: com.topjohnwu.superuser.NoShellException -> L43
            boolean r2 = r1.isRoot()     // Catch: com.topjohnwu.superuser.NoShellException -> L43
            if (r2 != 0) goto L40
            goto L41
        L40:
            r3 = r1
        L41:
            r1 = r3
            goto L44
        L43:
        L44:
            if (r1 != 0) goto L59
            boolean r1 = r6.hasFlags(r0)
            if (r1 != 0) goto L4f
            com.topjohnwu.superuser.internal.Utils.setConfirmedRootState(r4)
        L4f:
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r1 = "sh"
            r0[r4] = r1
            com.topjohnwu.superuser.internal.ShellImpl r1 = r6.build(r0)
        L59:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.topjohnwu.superuser.internal.BuilderImpl.build():com.topjohnwu.superuser.internal.ShellImpl");
    }

    @Override // com.topjohnwu.superuser.Shell.Builder
    @NonNull
    public ShellImpl build(String... strArr) {
        try {
            Utils.log(TAG, "exec " + TextUtils.join(" ", strArr));
            return build(Runtime.getRuntime().exec(strArr));
        } catch (IOException e) {
            Utils.ex(e);
            throw new NoShellException("Unable to create a shell!", e);
        }
    }

    @Override // com.topjohnwu.superuser.Shell.Builder
    @NonNull
    public ShellImpl build(Process process) {
        Shell.Initializer[] initializerArr;
        try {
            ShellImpl shellImpl = new ShellImpl(this, process);
            MainShell.setCached(shellImpl);
            if (this.initializers != null) {
                Context context = Utils.getContext();
                for (Shell.Initializer initializer : this.initializers) {
                    if (initializer != null && !initializer.onInit(context, shellImpl)) {
                        MainShell.setCached(null);
                        throw new NoShellException("Unable to init shell");
                    }
                }
            }
            return shellImpl;
        } catch (IOException e) {
            Utils.ex(e);
            throw new NoShellException("Unable to create a shell!", e);
        }
    }
}
