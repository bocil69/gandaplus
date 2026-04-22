package com.lody.virtual.server.accounts;

import android.accounts.Account;
import android.accounts.AuthenticatorDescription;
import android.accounts.IAccountManagerResponse;
import android.os.Bundle;
import android.os.RemoteException;

import com.lody.virtual.server.interfaces.IAccountManager;

import java.util.Collections;
import java.util.Map;

/**
 * Stub: all account management is disabled.
 * Extends IAccountManager.Stub so it can be registered in ServiceCache.
 * All methods return empty/null/no-op results.
 */
public class VAccountManagerService extends IAccountManager.Stub {

    private static final VAccountManagerService sInstance = new VAccountManagerService();

    public static VAccountManagerService get() {
        return sInstance;
    }

    public static void systemReady() {
        // no-op
    }

    // ── AIDL interface methods (all no-op) ─────────────────────────────

    @Override
    public AuthenticatorDescription[] getAuthenticatorTypes(int userId) throws RemoteException {
        return new AuthenticatorDescription[0];
    }

    @Override
    public void getAccountsByFeatures(int userId, IAccountManagerResponse response, String type, String[] features) throws RemoteException {}

    @Override
    public String getPreviousName(int userId, Account account) throws RemoteException { return null; }

    @Override
    public Account[] getAccounts(int userId, String type) throws RemoteException {
        return new Account[0];
    }

    @Override
    public void getAuthToken(int userId, IAccountManagerResponse response, Account account, String authTokenType, boolean notifyOnAuthFailure, boolean expectActivityLaunch, Bundle loginOptions) throws RemoteException {}

    @Override
    public void setPassword(int userId, Account account, String password) throws RemoteException {}

    @Override
    public void setAuthToken(int userId, Account account, String authTokenType, String authToken) throws RemoteException {}

    @Override
    public void setUserData(int userId, Account account, String key, String value) throws RemoteException {}

    @Override
    public void hasFeatures(int userId, IAccountManagerResponse response, Account account, String[] features) throws RemoteException {}

    @Override
    public void updateCredentials(int userId, IAccountManagerResponse response, Account account, String authTokenType, boolean expectActivityLaunch, Bundle loginOptions) throws RemoteException {}

    @Override
    public void editProperties(int userId, IAccountManagerResponse response, String accountType, boolean expectActivityLaunch) throws RemoteException {}

    @Override
    public void getAuthTokenLabel(int userId, IAccountManagerResponse response, String accountType, String authTokenType) throws RemoteException {}

    @Override
    public String getUserData(int userId, Account account, String key) throws RemoteException { return null; }

    @Override
    public String getPassword(int userId, Account account) throws RemoteException { return null; }

    @Override
    public void confirmCredentials(int userId, IAccountManagerResponse response, Account account, Bundle options, boolean expectActivityLaunch) throws RemoteException {}

    @Override
    public void addAccount(int userId, IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle optionsIn) throws RemoteException {}

    @Override
    public boolean addAccountExplicitly(int userId, Account account, String password, Bundle extras) throws RemoteException { return false; }

    @Override
    public boolean removeAccountExplicitly(int userId, Account account) throws RemoteException { return false; }

    @Override
    public void renameAccount(int userId, IAccountManagerResponse response, Account accountToRename, String newName) throws RemoteException {}

    @Override
    public void removeAccount(int userId, IAccountManagerResponse response, Account account, boolean expectActivityLaunch) throws RemoteException {}

    @Override
    public void clearPassword(int userId, Account account) throws RemoteException {}

    @Override
    public boolean accountAuthenticated(int userId, Account account) throws RemoteException { return false; }

    @Override
    public void invalidateAuthToken(int userId, String accountType, String authToken) throws RemoteException {}

    @Override
    public String peekAuthToken(int userId, Account account, String authTokenType) throws RemoteException { return null; }

    @Override
    public boolean setAccountVisibility(int userId, Account a, String packageName, int newVisibility) throws RemoteException { return false; }

    @Override
    public int getAccountVisibility(int userId, Account a, String packageName) throws RemoteException { return 0; }

    @Override
    public void startAddAccountSession(IAccountManagerResponse response, String accountType, String authTokenType, String[] requiredFeatures, boolean expectActivityLaunch, Bundle options) throws RemoteException {}

    @Override
    public void startUpdateCredentialsSession(IAccountManagerResponse response, Account account, String authTokenType, boolean expectActivityLaunch, Bundle options) throws RemoteException {}

    @Override
    public void registerAccountListener(String[] accountTypes, String opPackageName) throws RemoteException {}

    @Override
    public void unregisterAccountListener(String[] accountTypes, String opPackageName) throws RemoteException {}

    @Override
    public Map getPackagesAndVisibilityForAccount(int userId, Account account) throws RemoteException { return Collections.emptyMap(); }

    @Override
    public Map getAccountsAndVisibilityForPackage(int userId, String packageName, String accountType) throws RemoteException { return Collections.emptyMap(); }

    @Override
    public void finishSessionAsUser(IAccountManagerResponse response, Bundle sessionBundle, boolean expectActivityLaunch, Bundle appInfo, int userId) throws RemoteException {}

    @Override
    public void isCredentialsUpdateSuggested(IAccountManagerResponse response, Account account, String statusToken) throws RemoteException {}

    @Override
    public boolean addAccountExplicitlyWithVisibility(int userId, Account account, String password, Bundle extras, Map visibility) throws RemoteException { return false; }

    // ── Non-AIDL helper methods used by other server classes ───────────

    public AccountAndUser[] getAllAccounts() {
        return new AccountAndUser[0];
    }

    public void removeUserAccounts(int userId) {
        // no-op
    }

    public void refreshAuthenticatorCache(String packageName) {
        // no-op
    }
}
