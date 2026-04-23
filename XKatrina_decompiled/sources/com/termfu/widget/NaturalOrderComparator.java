package com.termfu.widget;
/* loaded from: classes6.dex */
public class NaturalOrderComparator {
    private static int compareRight(String str, String str2) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            char charAt = charAt(str, i);
            char charAt2 = charAt(str2, i2);
            if (!isDigit(charAt) && !isDigit(charAt2)) {
                return i3;
            }
            if (!isDigit(charAt)) {
                return -1;
            }
            if (!isDigit(charAt2)) {
                return 1;
            }
            if (charAt == 0 && charAt2 == 0) {
                return i3;
            }
            if (i3 == 0) {
                if (charAt < charAt2) {
                    i3 = -1;
                } else if (charAt > charAt2) {
                    i3 = 1;
                }
            }
            i++;
            i2++;
        }
    }

    public static int compare(Object obj, Object obj2) {
        int compareRight;
        String obj3 = obj.toString();
        String obj4 = obj2.toString();
        int i = 0;
        int i2 = 0;
        while (true) {
            char charAt = charAt(obj3, i);
            char charAt2 = charAt(obj4, i2);
            int i3 = 0;
            while (true) {
                if (!Character.isSpaceChar(charAt) && charAt != '0') {
                    break;
                }
                i3 = charAt == '0' ? i3 + 1 : 0;
                i++;
                charAt = charAt(obj3, i);
            }
            int i4 = 0;
            while (true) {
                if (!Character.isSpaceChar(charAt2) && charAt2 != '0') {
                    break;
                }
                i4 = charAt2 == '0' ? i4 + 1 : 0;
                i2++;
                charAt2 = charAt(obj4, i2);
            }
            if (Character.isDigit(charAt) && Character.isDigit(charAt2) && (compareRight = compareRight(obj3.substring(i), obj4.substring(i2))) != 0) {
                return compareRight;
            }
            if (charAt == 0 && charAt2 == 0) {
                return compareEqual(obj3, obj4, i3, i4);
            }
            if (charAt < charAt2) {
                return -1;
            }
            if (charAt > charAt2) {
                return 1;
            }
            i++;
            i2++;
        }
    }

    private static boolean isDigit(char c) {
        return Character.isDigit(c) || c == '.' || c == ',';
    }

    private static char charAt(String str, int i) {
        if (i >= str.length()) {
            return (char) 0;
        }
        return Character.toLowerCase(str.charAt(i));
    }

    private static int compareEqual(String str, String str2, int i, int i2) {
        int i3 = i - i2;
        if (i3 != 0) {
            return i3;
        }
        if (str.length() == str2.length()) {
            return str.compareToIgnoreCase(str2);
        }
        return str.length() - str2.length();
    }
}
