package com.termfu.app;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.termfu.app.ExtraKeysInfos;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ExtraKeysInfos.java */
/* loaded from: classes6.dex */
public class ExtraKeyButton {
    private String display;
    private String key;
    private boolean macro;
    @Nullable
    private ExtraKeyButton popup;

    public ExtraKeyButton(ExtraKeysInfos.CharDisplayMap charDisplayMap, JSONObject jSONObject) throws JSONException {
        this(charDisplayMap, jSONObject, null);
    }

    public ExtraKeyButton(final ExtraKeysInfos.CharDisplayMap charDisplayMap, JSONObject jSONObject, ExtraKeyButton extraKeyButton) throws JSONException {
        String[] split;
        this.popup = null;
        String optString = jSONObject.optString("key", null);
        String optString2 = jSONObject.optString("macro", null);
        if (optString != null && optString2 != null) {
            throw new JSONException("Both key and macro can't be set for the same key");
        }
        if (optString != null) {
            split = new String[]{optString};
            this.macro = false;
        } else if (optString2 != null) {
            split = optString2.split(" ");
            this.macro = true;
        } else {
            throw new JSONException("All keys have to specify either key or macro");
        }
        for (int i = 0; i < split.length; i++) {
            split[i] = ExtraKeysInfos.replaceAlias(split[i]);
        }
        this.key = TextUtils.join(" ", split);
        String optString3 = jSONObject.optString("display", null);
        if (optString3 != null) {
            this.display = optString3;
        } else {
            this.display = (String) Arrays.stream(split).map(new Function() { // from class: com.termfu.app.ExtraKeyButton$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String str;
                    str = ExtraKeysInfos.CharDisplayMap.this.get((String) obj, r2);
                    return str;
                }
            }).collect(Collectors.joining(" "));
        }
        this.popup = extraKeyButton;
    }

    public String getKey() {
        return this.key;
    }

    public boolean isMacro() {
        return this.macro;
    }

    public String getDisplay() {
        return this.display;
    }

    @Nullable
    public ExtraKeyButton getPopup() {
        return this.popup;
    }
}
