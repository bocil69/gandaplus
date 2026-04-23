package io.noties.markwon;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public class LinkResolverDef implements LinkResolver {
    private static final String DEFAULT_SCHEME = "https";

    @Override // io.noties.markwon.LinkResolver
    public void resolve(@NonNull View view, @NonNull String str) {
        Uri parseLink = parseLink(str);
        Context context = view.getContext();
        Intent intent = new Intent("android.intent.action.VIEW", parseLink);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Log.w("LinkResolverDef", "Actvity was not found for the link: '" + str + "'");
        }
    }

    @NonNull
    private static Uri parseLink(@NonNull String str) {
        Uri parse = Uri.parse(str);
        return TextUtils.isEmpty(parse.getScheme()) ? parse.buildUpon().scheme(DEFAULT_SCHEME).build() : parse;
    }
}
