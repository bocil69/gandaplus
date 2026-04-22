package mirror.android.telephony;

import mirror.RefClass;
import mirror.RefInt;
import mirror.RefObject;

public class SubscriptionInfo {
    public static Class<?> TYPE = RefClass.load(SubscriptionInfo.class, android.telephony.SubscriptionInfo.class);
    public static RefObject<CharSequence> mCarrierName;
    public static RefObject<String> mCountryIso;
    public static RefInt mMcc;
    public static RefInt mMnc;
}
