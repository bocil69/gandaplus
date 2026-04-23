package com.topjohnwu.superuser.internal;

import androidx.annotation.RestrictTo;
import com.topjohnwu.superuser.io.SuFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class IOFactory {
    static final byte[] JUNK = new byte[1];

    private IOFactory() {
    }

    public static ShellIO shellIO(SuFile suFile, String str) throws FileNotFoundException {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 114:
                if (str.equals("r")) {
                    c = 0;
                    break;
                }
                break;
            case 3653:
                if (str.equals("rw")) {
                    c = 1;
                    break;
                }
                break;
            case 113343:
                if (str.equals("rwd")) {
                    c = 2;
                    break;
                }
                break;
            case 113358:
                if (str.equals("rws")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                break;
            default:
                throw new IllegalArgumentException("Unknown mode: " + str);
            case 1:
            case 2:
            case 3:
                str = "rw";
                break;
        }
        return ShellIO.get(suFile, str);
    }

    public static RAFWrapper raf(File file, String str) throws FileNotFoundException {
        return new RAFWrapper(file, str);
    }

    public static InputStream fifoIn(SuFile suFile) throws FileNotFoundException {
        return ShellPipeStream.openReadStream(suFile);
    }

    public static OutputStream fifoOut(SuFile suFile, boolean z) throws FileNotFoundException {
        return ShellPipeStream.openWriteStream(suFile, z);
    }
}
