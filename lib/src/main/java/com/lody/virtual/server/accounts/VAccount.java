package com.lody.virtual.server.accounts;

import android.accounts.Account;

/**
 * Stub: minimal VAccount data class.
 */
public class VAccount {
    public Account account;
    public int userId;

    public VAccount(Account account, int userId) {
        this.account = account;
        this.userId = userId;
    }
}
