package com.stericson.RootTools.containers;
/* loaded from: classes6.dex */
public class Permissions {
    String group;
    String other;
    int permissions;
    String symlink;
    String type;
    String user;

    public String getSymlink() {
        return this.symlink;
    }

    public String getType() {
        return this.type;
    }

    public int getPermissions() {
        return this.permissions;
    }

    public String getUserPermissions() {
        return this.user;
    }

    public String getGroupPermissions() {
        return this.group;
    }

    public String getOtherPermissions() {
        return this.other;
    }

    public void setSymlink(String str) {
        this.symlink = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setPermissions(int i) {
        this.permissions = i;
    }

    public void setUserPermissions(String str) {
        this.user = str;
    }

    public void setGroupPermissions(String str) {
        this.group = str;
    }

    public void setOtherPermissions(String str) {
        this.other = str;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String str) {
        this.user = str;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public String getOther() {
        return this.other;
    }

    public void setOther(String str) {
        this.other = str;
    }
}
