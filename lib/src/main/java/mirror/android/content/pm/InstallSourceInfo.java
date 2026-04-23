package mirror.android.content.pm;

import mirror.MethodReflectParams;
import mirror.RefClass;
import mirror.RefConstructor;

public class InstallSourceInfo {

    public static Class<?> TYPE = RefClass.load(InstallSourceInfo.class, "android.content.pm.InstallSourceInfo");

    @MethodReflectParams({"java.lang.String", "android.content.pm.SigningInfo", "java.lang.String", "java.lang.String"})
    public static RefConstructor<android.content.pm.InstallSourceInfo> ctorApi30;

    @MethodReflectParams({"java.lang.String", "android.content.pm.SigningInfo", "java.lang.String", "java.lang.String", "java.lang.String", "int"})
    public static RefConstructor<android.content.pm.InstallSourceInfo> ctorApi33;
}
