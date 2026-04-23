package com.google.zxing.client.result;

import com.google.zxing.Result;
/* loaded from: classes2.dex */
public final class WifiResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public WifiParsedResult parse(Result result) {
        String rawText;
        String ssid;
        String rawText2 = getMassagedText(result);
        if (!rawText2.startsWith("WIFI:") || (ssid = matchSinglePrefixedField("S:", (rawText = rawText2.substring(5)), ';', false)) == null || ssid.isEmpty()) {
            return null;
        }
        String pass = matchSinglePrefixedField("P:", rawText, ';', false);
        String type = matchSinglePrefixedField("T:", rawText, ';', false);
        if (type == null) {
            type = "nopass";
        }
        boolean hidden = false;
        String phase2Method = matchSinglePrefixedField("PH2:", rawText, ';', false);
        String hValue = matchSinglePrefixedField("H:", rawText, ';', false);
        if (hValue != null) {
            if (phase2Method != null || "true".equalsIgnoreCase(hValue) || "false".equalsIgnoreCase(hValue)) {
                hidden = Boolean.parseBoolean(hValue);
            } else {
                phase2Method = hValue;
            }
        }
        String identity = matchSinglePrefixedField("I:", rawText, ';', false);
        String anonymousIdentity = matchSinglePrefixedField("A:", rawText, ';', false);
        String eapMethod = matchSinglePrefixedField("E:", rawText, ';', false);
        return new WifiParsedResult(type, ssid, pass, hidden, identity, anonymousIdentity, eapMethod, phase2Method);
    }
}
