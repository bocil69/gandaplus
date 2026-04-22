package com.lody.virtual.server.accounts;

import android.accounts.Account;

/**
 * Stub: minimal data class for account+userId pair.
 * Used as HashMap key in SyncStorageEngine and SyncManager.
 */
public class AccountAndUser {
    public final Account account;
    public final int userId;

    public AccountAndUser(Account account, int userId) {
        this.account = account;
        this.userId = userId;
    }

    public AccountAndUser(String accountName, int userId) {
        this.account = new Account(accountName, "stub");
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountAndUser that = (AccountAndUser) o;
        return userId == that.userId && (account != null ? account.equals(that.account) : that.account == null);
    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + userId;
        return result;
    }
}
