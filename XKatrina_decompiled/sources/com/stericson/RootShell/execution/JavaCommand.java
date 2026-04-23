package com.stericson.RootShell.execution;

import android.content.Context;
/* loaded from: classes6.dex */
public class JavaCommand extends Command {
    @Override // com.stericson.RootShell.execution.Command
    public void commandCompleted(int i, int i2) {
    }

    @Override // com.stericson.RootShell.execution.Command
    public void commandTerminated(int i, String str) {
    }

    public JavaCommand(int i, Context context, String... strArr) {
        super(i, strArr);
        this.context = context;
        this.javaCommand = true;
    }

    public JavaCommand(int i, boolean z, Context context, String... strArr) {
        super(i, z, strArr);
        this.context = context;
        this.javaCommand = true;
    }

    public JavaCommand(int i, int i2, Context context, String... strArr) {
        super(i, i2, strArr);
        this.context = context;
        this.javaCommand = true;
    }

    @Override // com.stericson.RootShell.execution.Command
    public void commandOutput(int i, String str) {
        super.commandOutput(i, str);
    }
}
