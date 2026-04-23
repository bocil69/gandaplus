package com.stericson.RootShell.execution;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.stericson.RootShell.RootShell;
import java.io.IOException;
/* loaded from: classes6.dex */
public class Command {
    String[] command;
    protected Context context;
    boolean executing;
    ExecutionMonitor executionMonitor;
    int exitCode;
    boolean finished;
    boolean handlerEnabled;
    int id;
    protected boolean javaCommand;
    Handler mHandler;
    boolean terminated;
    int timeout;
    public int totalOutput;
    public int totalOutputProcessed;
    protected boolean used;

    public void commandCompleted(int i, int i2) {
    }

    public void commandTerminated(int i, String str) {
    }

    public Command(int i, String... strArr) {
        this.javaCommand = false;
        this.context = null;
        this.totalOutput = 0;
        this.totalOutputProcessed = 0;
        this.executionMonitor = null;
        this.mHandler = null;
        this.used = false;
        this.executing = false;
        this.command = new String[0];
        this.finished = false;
        this.terminated = false;
        this.handlerEnabled = true;
        this.exitCode = -1;
        this.id = 0;
        this.timeout = RootShell.defaultCommandTimeout;
        this.command = strArr;
        this.id = i;
        createHandler(RootShell.handlerEnabled);
    }

    public Command(int i, boolean z, String... strArr) {
        this.javaCommand = false;
        this.context = null;
        this.totalOutput = 0;
        this.totalOutputProcessed = 0;
        this.executionMonitor = null;
        this.mHandler = null;
        this.used = false;
        this.executing = false;
        this.command = new String[0];
        this.finished = false;
        this.terminated = false;
        this.handlerEnabled = true;
        this.exitCode = -1;
        this.id = 0;
        this.timeout = RootShell.defaultCommandTimeout;
        this.command = strArr;
        this.id = i;
        createHandler(z);
    }

    public Command(int i, int i2, String... strArr) {
        this.javaCommand = false;
        this.context = null;
        this.totalOutput = 0;
        this.totalOutputProcessed = 0;
        this.executionMonitor = null;
        this.mHandler = null;
        this.used = false;
        this.executing = false;
        this.command = new String[0];
        this.finished = false;
        this.terminated = false;
        this.handlerEnabled = true;
        this.exitCode = -1;
        this.id = 0;
        int i3 = RootShell.defaultCommandTimeout;
        this.command = strArr;
        this.id = i;
        this.timeout = i2;
        createHandler(RootShell.handlerEnabled);
    }

    public void commandOutput(int i, String str) {
        RootShell.log("Command", "ID: " + i + ", " + str);
        this.totalOutputProcessed = this.totalOutputProcessed + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void commandFinished() {
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            Handler handler = this.mHandler;
            if (handler != null && this.handlerEnabled) {
                Message obtainMessage = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("action", 2);
                obtainMessage.setData(bundle);
                this.mHandler.sendMessage(obtainMessage);
            } else {
                commandCompleted(this.id, this.exitCode);
            }
            RootShell.log("Command " + this.id + " finished.");
            finishCommand();
        }
    }

    private void createHandler(boolean z) {
        this.handlerEnabled = z;
        if (Looper.myLooper() != null && z) {
            RootShell.log("CommandHandler created");
            this.mHandler = new CommandHandler(this, null);
            return;
        }
        RootShell.log("CommandHandler not created");
    }

    public final void finish() {
        RootShell.log("Command finished at users request!");
        commandFinished();
    }

    protected final void finishCommand() {
        this.executing = false;
        this.finished = true;
        notifyAll();
    }

    public final String getCommand() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (this.javaCommand) {
            String path = this.context.getFilesDir().getPath();
            while (i < this.command.length) {
                if (Build.VERSION.SDK_INT > 22) {
                    sb.append("export CLASSPATH=" + path + "/anbuild.dex; app_process /system/bin " + this.command[i]);
                } else {
                    sb.append("dalvikvm -cp " + path + "/anbuild.dex com.android.internal.util.WithFramework com.stericson.RootTools.containers.RootClass " + this.command[i]);
                }
                sb.append('\n');
                i++;
            }
        } else {
            while (true) {
                String[] strArr = this.command;
                if (i >= strArr.length) {
                    break;
                }
                sb.append(strArr[i]);
                sb.append('\n');
                i++;
            }
        }
        return sb.toString();
    }

    public final boolean isExecuting() {
        return this.executing;
    }

    public final boolean isHandlerEnabled() {
        return this.handlerEnabled;
    }

    public final boolean isFinished() {
        return this.finished;
    }

    public final int getExitCode() {
        return this.exitCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setExitCode(int i) {
        synchronized (this) {
            this.exitCode = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void startExecution() {
        this.used = true;
        ExecutionMonitor executionMonitor = new ExecutionMonitor(this);
        this.executionMonitor = executionMonitor;
        executionMonitor.setPriority(1);
        this.executionMonitor.start();
        this.executing = true;
    }

    public final void terminate() {
        RootShell.log("Terminating command at users request!");
        terminated("Terminated at users request!");
    }

    protected final void terminate(String str) {
        try {
            Shell.closeAll();
            RootShell.log("Terminating all shells.");
            terminated(str);
        } catch (IOException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void terminated(String str) {
        synchronized (this) {
            Handler handler = this.mHandler;
            if (handler != null && this.handlerEnabled) {
                Message obtainMessage = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("action", 3);
                bundle.putString("text", str);
                obtainMessage.setData(bundle);
                this.mHandler.sendMessage(obtainMessage);
            } else {
                commandTerminated(this.id, str);
            }
            RootShell.log("Command " + this.id + " did not finish because it was terminated. Termination reason: " + str);
            setExitCode(-1);
            this.terminated = true;
            finishCommand();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void output(int i, String str) {
        this.totalOutput++;
        Handler handler = this.mHandler;
        if (handler != null && this.handlerEnabled) {
            Message obtainMessage = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putInt("action", 1);
            bundle.putString("text", str);
            obtainMessage.setData(bundle);
            this.mHandler.sendMessage(obtainMessage);
            return;
        }
        commandOutput(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class ExecutionMonitor extends Thread {
        private final Command command;

        public ExecutionMonitor(Command command) {
            this.command = command;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (this.command.timeout > 0) {
                synchronized (this.command) {
                    try {
                        RootShell.log("Command " + this.command.id + " is waiting for: " + this.command.timeout);
                        Command command = this.command;
                        command.wait((long) command.timeout);
                    } catch (InterruptedException e) {
                        RootShell.log("Exception: " + e);
                    }
                    if (!this.command.isFinished()) {
                        RootShell.log("Timeout Exception has occurred for command: " + this.command.id + ".");
                        Command.this.terminate("Timeout Exception");
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class CommandHandler extends Handler {
        public static final String ACTION = "action";
        public static final int COMMAND_COMPLETED = 2;
        public static final int COMMAND_OUTPUT = 1;
        public static final int COMMAND_TERMINATED = 3;
        public static final String TEXT = "text";

        private CommandHandler() {
        }

        /* synthetic */ CommandHandler(Command command, CommandHandler commandHandler) {
            this();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.getData().getInt("action");
            String string = message.getData().getString("text");
            if (i == 1) {
                Command command = Command.this;
                command.commandOutput(command.id, string);
            } else if (i == 2) {
                Command command2 = Command.this;
                command2.commandCompleted(command2.id, Command.this.exitCode);
            } else if (i != 3) {
            } else {
                Command command3 = Command.this;
                command3.commandTerminated(command3.id, string);
            }
        }
    }
}
