package com.topjohnwu.superuser.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class StreamGobbler<T> implements Callable<T> {
    private static final String TAG = "SHELLOUT";
    protected final InputStream in;
    protected final List<String> list;

    StreamGobbler(InputStream inputStream, List<String> list) {
        this.in = inputStream;
        this.list = list;
    }

    private boolean outputAndCheck(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        int i = length - 36;
        boolean startsWith = str.startsWith(JobImpl.END_UUID, i);
        if (startsWith) {
            if (length == 36) {
                return false;
            }
            str = str.substring(0, i);
        }
        List<String> list = this.list;
        if (list != null) {
            list.add(str);
            Utils.log(TAG, str);
        }
        return !startsWith;
    }

    protected String process(boolean z) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.in, StandardCharsets.UTF_8));
        do {
        } while (outputAndCheck(bufferedReader.readLine()));
        if (z) {
            return bufferedReader.readLine();
        }
        return null;
    }

    /* loaded from: classes2.dex */
    static class OUT extends StreamGobbler<Integer> {
        private static final int NO_RESULT_CODE = 1;

        /* JADX INFO: Access modifiers changed from: package-private */
        public OUT(InputStream inputStream, List<String> list) {
            super(inputStream, list);
        }

        @Override // java.util.concurrent.Callable
        public Integer call() throws Exception {
            int parseInt;
            String process = process(true);
            if (process == null) {
                parseInt = 1;
            } else {
                try {
                    parseInt = Integer.parseInt(process);
                } catch (NumberFormatException unused) {
                    return 1;
                }
            }
            Utils.log(StreamGobbler.TAG, "(exit code: " + parseInt + ")");
            return Integer.valueOf(parseInt);
        }
    }

    /* loaded from: classes2.dex */
    static class ERR extends StreamGobbler<Void> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ERR(InputStream inputStream, List<String> list) {
            super(inputStream, list);
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            process(false);
            return null;
        }
    }
}
