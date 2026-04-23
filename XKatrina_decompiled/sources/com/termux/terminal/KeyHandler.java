package com.termux.terminal;

import androidx.exifinterface.media.ExifInterface;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public final class KeyHandler {
    public static final int KEYMOD_ALT = Integer.MIN_VALUE;
    public static final int KEYMOD_CTRL = 1073741824;
    public static final int KEYMOD_NUM_LOCK = 268435456;
    public static final int KEYMOD_SHIFT = 536870912;
    private static final Map<String, Integer> TERMCAP_TO_KEYCODE;

    static {
        HashMap hashMap = new HashMap();
        TERMCAP_TO_KEYCODE = hashMap;
        hashMap.put("%i", 536870934);
        hashMap.put("#2", 536871034);
        hashMap.put("#4", 536870933);
        hashMap.put("*7", 536871035);
        hashMap.put("k1", 131);
        hashMap.put("k2", 132);
        hashMap.put("k3", 133);
        hashMap.put("k4", 134);
        hashMap.put("k5", 135);
        hashMap.put("k6", 136);
        hashMap.put("k7", 137);
        hashMap.put("k8", 138);
        hashMap.put("k9", 139);
        hashMap.put("k;", 140);
        hashMap.put("F1", 141);
        hashMap.put("F2", 142);
        hashMap.put("F3", 536871043);
        hashMap.put("F4", 536871044);
        hashMap.put("F5", 536871045);
        hashMap.put("F6", 536871046);
        hashMap.put("F7", 536871047);
        hashMap.put("F8", 536871048);
        hashMap.put("F9", 536871049);
        hashMap.put("FA", 536871050);
        hashMap.put("FB", 536871051);
        hashMap.put("FC", 536871052);
        hashMap.put("FD", 536871053);
        hashMap.put("FE", 536871054);
        hashMap.put("kb", 67);
        hashMap.put("kd", 20);
        hashMap.put("kh", 122);
        hashMap.put("kl", 21);
        hashMap.put("kr", 22);
        hashMap.put("K1", 122);
        hashMap.put("K3", 92);
        hashMap.put("K4", 123);
        hashMap.put("K5", 93);
        hashMap.put("ku", 19);
        hashMap.put("kB", 536870973);
        hashMap.put("kD", 112);
        hashMap.put("kDN", 536870932);
        hashMap.put("kF", 536870932);
        hashMap.put("kI", 124);
        hashMap.put("kN", 92);
        hashMap.put("kP", 93);
        hashMap.put("kR", 536870931);
        hashMap.put("kUP", 536870931);
        hashMap.put("@7", 123);
        hashMap.put("@8", 160);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCodeFromTermcap(String str, boolean z, boolean z2) {
        Integer num = TERMCAP_TO_KEYCODE.get(str);
        if (num == null) {
            return null;
        }
        int intValue = num.intValue();
        int i = 0;
        if ((intValue & 536870912) != 0) {
            intValue &= -536870913;
            i = 536870912;
        }
        if ((intValue & 1073741824) != 0) {
            i |= 1073741824;
            intValue &= -1073741825;
        }
        if ((intValue & Integer.MIN_VALUE) != 0) {
            i |= Integer.MIN_VALUE;
            intValue &= Integer.MAX_VALUE;
        }
        if ((intValue & 268435456) != 0) {
            i |= 268435456;
            intValue &= -268435457;
        }
        return getCode(intValue, i, z, z2);
    }

    public static String getCode(int i, int i2, boolean z, boolean z2) {
        boolean z3 = (i2 & 268435456) != 0;
        int i3 = i2 & (-268435457);
        if (i != 4) {
            if (i == 61) {
                return (536870912 & i3) == 0 ? "\t" : "\u001b[Z";
            } else if (i == 62) {
                if ((i3 & 1073741824) == 0) {
                    return null;
                }
                return "\u0000";
            } else if (i == 66) {
                return (i3 & Integer.MIN_VALUE) == 0 ? "\r" : "\u001b\r";
            } else if (i == 67) {
                StringBuilder sb = new StringBuilder((i3 & Integer.MIN_VALUE) == 0 ? "" : "\u001b");
                sb.append((i3 & 1073741824) == 0 ? "\u007f" : "\b");
                return sb.toString();
            } else if (i == 92) {
                return "\u001b[5~";
            } else {
                if (i == 93) {
                    return "\u001b[6~";
                }
                if (i != 111) {
                    if (i != 112) {
                        switch (i) {
                            case 19:
                                if (i3 == 0) {
                                    return z ? "\u001bOA" : "\u001b[A";
                                }
                                return transformForModifiers("\u001b[1", i3, 'A');
                            case 20:
                                if (i3 == 0) {
                                    return z ? "\u001bOB" : "\u001b[B";
                                }
                                return transformForModifiers("\u001b[1", i3, 'B');
                            case 21:
                                if (i3 == 0) {
                                    return z ? "\u001bOD" : "\u001b[D";
                                }
                                return transformForModifiers("\u001b[1", i3, 'D');
                            case 22:
                                if (i3 == 0) {
                                    return z ? "\u001bOC" : "\u001b[C";
                                }
                                return transformForModifiers("\u001b[1", i3, 'C');
                            case 23:
                                return "\r";
                            default:
                                switch (i) {
                                    case 120:
                                        return "\u001b[32~";
                                    case 121:
                                        return "\u001b[34~";
                                    case 122:
                                        if (i3 == 0) {
                                            return z ? "\u001bOH" : "\u001b[H";
                                        }
                                        return transformForModifiers("\u001b[1", i3, 'H');
                                    case 123:
                                        if (i3 == 0) {
                                            return z ? "\u001bOF" : "\u001b[F";
                                        }
                                        return transformForModifiers("\u001b[1", i3, 'F');
                                    case 124:
                                        return transformForModifiers("\u001b[2", i3, '~');
                                    default:
                                        switch (i) {
                                            case 131:
                                                return i3 == 0 ? "\u001bOP" : transformForModifiers("\u001b[1", i3, 'P');
                                            case 132:
                                                return i3 == 0 ? "\u001bOQ" : transformForModifiers("\u001b[1", i3, 'Q');
                                            case 133:
                                                return i3 == 0 ? "\u001bOR" : transformForModifiers("\u001b[1", i3, 'R');
                                            case 134:
                                                return i3 == 0 ? "\u001bOS" : transformForModifiers("\u001b[1", i3, 'S');
                                            case 135:
                                                return transformForModifiers("\u001b[15", i3, '~');
                                            case 136:
                                                return transformForModifiers("\u001b[17", i3, '~');
                                            case 137:
                                                return transformForModifiers("\u001b[18", i3, '~');
                                            case 138:
                                                return transformForModifiers("\u001b[19", i3, '~');
                                            case 139:
                                                return transformForModifiers("\u001b[20", i3, '~');
                                            case 140:
                                                return transformForModifiers("\u001b[21", i3, '~');
                                            case 141:
                                                return transformForModifiers("\u001b[23", i3, '~');
                                            case 142:
                                                return transformForModifiers("\u001b[24", i3, '~');
                                            case 143:
                                                if (z2) {
                                                    return "\u001bOP";
                                                }
                                                return null;
                                            case 144:
                                                if (z3) {
                                                    return z2 ? transformForModifiers("\u001bO", i3, 'p') : "0";
                                                }
                                                return transformForModifiers("\u001b[2", i3, '~');
                                            case 145:
                                                if (z3) {
                                                    return z2 ? transformForModifiers("\u001bO", i3, 'q') : "1";
                                                } else if (i3 == 0) {
                                                    return z ? "\u001bOF" : "\u001b[F";
                                                } else {
                                                    return transformForModifiers("\u001b[1", i3, 'F');
                                                }
                                            case 146:
                                                if (z3) {
                                                    return z2 ? transformForModifiers("\u001bO", i3, 'r') : ExifInterface.GPS_MEASUREMENT_2D;
                                                } else if (i3 == 0) {
                                                    return z ? "\u001bOB" : "\u001b[B";
                                                } else {
                                                    return transformForModifiers("\u001b[1", i3, 'B');
                                                }
                                            case 147:
                                                return z3 ? z2 ? transformForModifiers("\u001bO", i3, 's') : ExifInterface.GPS_MEASUREMENT_3D : "\u001b[6~";
                                            case 148:
                                                if (z3) {
                                                    return z2 ? transformForModifiers("\u001bO", i3, 't') : "4";
                                                } else if (i3 == 0) {
                                                    return z ? "\u001bOD" : "\u001b[D";
                                                } else {
                                                    return transformForModifiers("\u001b[1", i3, 'D');
                                                }
                                            case 149:
                                                return z2 ? transformForModifiers("\u001bO", i3, 'u') : "5";
                                            case 150:
                                                if (z3) {
                                                    return z2 ? transformForModifiers("\u001bO", i3, 'v') : "6";
                                                } else if (i3 == 0) {
                                                    return z ? "\u001bOC" : "\u001b[C";
                                                } else {
                                                    return transformForModifiers("\u001b[1", i3, 'C');
                                                }
                                            case 151:
                                                if (z3) {
                                                    return z2 ? transformForModifiers("\u001bO", i3, 'w') : "7";
                                                } else if (i3 == 0) {
                                                    return z ? "\u001bOH" : "\u001b[H";
                                                } else {
                                                    return transformForModifiers("\u001b[1", i3, 'H');
                                                }
                                            case 152:
                                                if (z3) {
                                                    return z2 ? transformForModifiers("\u001bO", i3, 'x') : "8";
                                                } else if (i3 == 0) {
                                                    return z ? "\u001bOA" : "\u001b[A";
                                                } else {
                                                    return transformForModifiers("\u001b[1", i3, 'A');
                                                }
                                            case 153:
                                                return z3 ? z2 ? transformForModifiers("\u001bO", i3, 'y') : "9" : "\u001b[5~";
                                            case 154:
                                                return z2 ? transformForModifiers("\u001bO", i3, 'o') : "/";
                                            case 155:
                                                return z2 ? transformForModifiers("\u001bO", i3, 'j') : "*";
                                            case 156:
                                                return z2 ? transformForModifiers("\u001bO", i3, 'm') : "-";
                                            case 157:
                                                return z2 ? transformForModifiers("\u001bO", i3, 'k') : "+";
                                            case 158:
                                                if (z3) {
                                                    return z2 ? "\u001bOn" : ".";
                                                }
                                                return transformForModifiers("\u001b[3", i3, '~');
                                            case 159:
                                                return ",";
                                            case 160:
                                                return z2 ? transformForModifiers("\u001bO", i3, 'M') : "\n";
                                            case 161:
                                                return z2 ? transformForModifiers("\u001bO", i3, 'X') : "=";
                                            default:
                                                return null;
                                        }
                                }
                        }
                    }
                    return transformForModifiers("\u001b[3", i3, '~');
                }
            }
        }
        return "\u001b";
    }

    private static String transformForModifiers(String str, int i, char c) {
        int i2;
        if (i == Integer.MIN_VALUE) {
            i2 = 3;
        } else if (i == -1610612736) {
            i2 = 4;
        } else if (i == -1073741824) {
            i2 = 7;
        } else if (i == -536870912) {
            i2 = 8;
        } else if (i == 536870912) {
            i2 = 2;
        } else if (i == 1073741824) {
            i2 = 5;
        } else if (i != 1610612736) {
            return String.valueOf(str) + c;
        } else {
            i2 = 6;
        }
        return String.valueOf(str) + ";" + i2 + c;
    }
}
