package com.topjohnwu.superuser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.topjohnwu.superuser.internal.UiThreadHandler;
import java.util.AbstractList;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public abstract class CallbackList<E> extends AbstractList<E> {
    protected List<E> mBase;
    protected Executor mExecutor;

    /* renamed from: onAddElement */
    public abstract void m131lambda$add$0$comtopjohnwusuperuserCallbackList(E e);

    protected CallbackList() {
        this(UiThreadHandler.executor, null);
    }

    protected CallbackList(@Nullable List<E> list) {
        this(UiThreadHandler.executor, list);
    }

    protected CallbackList(@NonNull Executor executor) {
        this(executor, null);
    }

    protected CallbackList(@NonNull Executor executor, @Nullable List<E> list) {
        this.mExecutor = executor;
        this.mBase = list;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        List<E> list = this.mBase;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        List<E> list = this.mBase;
        if (list == null) {
            return null;
        }
        return list.set(i, e);
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, final E e) {
        List<E> list = this.mBase;
        if (list != null) {
            list.add(i, e);
        }
        this.mExecutor.execute(new Runnable() { // from class: com.topjohnwu.superuser.CallbackList$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CallbackList.this.m131lambda$add$0$comtopjohnwusuperuserCallbackList(e);
            }
        });
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i) {
        List<E> list = this.mBase;
        if (list == null) {
            return null;
        }
        return list.remove(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        List<E> list = this.mBase;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
