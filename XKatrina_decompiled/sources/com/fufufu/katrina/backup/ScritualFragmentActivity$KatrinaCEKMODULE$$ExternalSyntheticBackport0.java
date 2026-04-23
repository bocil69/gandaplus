package com.fufufu.katrina.backup;

import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.Iterator;
/* compiled from: D8$$SyntheticClass */
@SynthesizedClassV2(kind = 23, versionHash = "79350b666c61fb98f585652cf8eb3be7850d2ab8c16c1e890d0171be2ca2d761")
/* loaded from: classes5.dex */
public final /* synthetic */ class ScritualFragmentActivity$KatrinaCEKMODULE$$ExternalSyntheticBackport0 {
    public static /* synthetic */ String m(CharSequence charSequence, Iterable iterable) {
        if (charSequence != null) {
            StringBuilder sb = new StringBuilder();
            Iterator it = iterable.iterator();
            if (it.hasNext()) {
                while (true) {
                    sb.append((CharSequence) it.next());
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(charSequence);
                }
            }
            return sb.toString();
        }
        throw new NullPointerException("delimiter");
    }
}
