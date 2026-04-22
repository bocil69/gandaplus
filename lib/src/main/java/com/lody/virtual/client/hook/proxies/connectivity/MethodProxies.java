package com.lody.virtual.client.hook.proxies.connectivity;

import android.util.Log;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;

import java.lang.reflect.Method;

/**
 * @Date 18-1-20 15
 * @Author lxf@xdja.com
 * @Descrip:
 */
public class MethodProxies {

    static class ReplacePackageAndUidProxy extends MethodProxy {

        private final String methodName;

        ReplacePackageAndUidProxy(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public String getMethodName() {
            return methodName;
        }

        @Override
        public boolean beforeCall(Object who, Method method, Object... args) {
            int pkgIndex = -1;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String && VirtualCore.get().isAppInstalled((String) args[i])) {
                    pkgIndex = i;
                    break;
                }
            }
            String replacedPkg = MethodParameterUtils.replaceAllAppPkgWithHost(args);
            if (replacedPkg != null && pkgIndex > 0) {
                for (int i = pkgIndex - 1; i >= 0; i--) {
                    if (args[i] instanceof Integer) {
                        args[i] = VirtualCore.get().myUid();
                        break;
                    }
                }
            }
            return super.beforeCall(who, method, args);
        }

        @Override
        public boolean isEnable() {
            return isAppProcess();
        }
    }

    static class PrepareVpn extends MethodProxy {

        @Override
        public String getMethodName() {
            return "prepareVpn";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {

            args[0] = VirtualCore.get().getHostPkg();
//          args[2] = getRealUid();

            String oldname = (String) args[0];
            String newName = (String) args[1];
            int userid = (int)args[2];

            return method.invoke(who, args);
        }

        @Override
        public Object afterCall(Object who, Method method, Object[] args, Object result) throws Throwable {
            String oldname = (String) args[0];
//            if(oldname.equals("com.xdja.safeclient"))
//                return true;

            return super.afterCall(who, method, args, result);
        }

        @Override
        public boolean isEnable() {
            return isAppProcess();
        }
    }

}
