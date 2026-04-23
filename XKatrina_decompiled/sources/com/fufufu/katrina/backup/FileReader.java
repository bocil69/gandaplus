package com.fufufu.katrina.backup;

import androidx.exifinterface.media.ExifInterface;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes5.dex */
public class FileReader {
    public static ArrayList<HashMap<String, Object>> executeShellCommand(String str) {
        List<String> out = Shell.cmd(str).exec().getOut();
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for (String str2 : out) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(ClientCookie.PATH_ATTR, str2.replace(" directory", "").replace(" regular file", "").replace(" regular empty file", "").replace(" symbolic link", "").replace("//", "/").replace("1 /", "/").replace("2 /", "/"));
            hashMap.put("pick", "false");
            if (str2.contains(" symbolic link")) {
                hashMap.put("type", "L");
            } else if (str2.contains(" directory")) {
                hashMap.put("type", "D");
            } else if (str2.contains(" regular file")) {
                hashMap.put("type", "F");
            } else if (str2.contains(" regular empty file")) {
                hashMap.put("type", ExifInterface.LONGITUDE_EAST);
            } else {
                hashMap.put("type", "?");
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }
}
