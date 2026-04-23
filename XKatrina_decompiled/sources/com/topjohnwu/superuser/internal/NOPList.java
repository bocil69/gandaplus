package com.topjohnwu.superuser.internal;

import java.util.AbstractList;
/* loaded from: classes2.dex */
public class NOPList extends AbstractList<String> {
    private static NOPList list;

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, String str) {
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int i) {
        return null;
    }

    @Override // java.util.AbstractList, java.util.List
    public String remove(int i) {
        return null;
    }

    @Override // java.util.AbstractList, java.util.List
    public String set(int i, String str) {
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return 0;
    }

    public static NOPList getInstance() {
        if (list == null) {
            list = new NOPList();
        }
        return list;
    }

    private NOPList() {
    }
}
