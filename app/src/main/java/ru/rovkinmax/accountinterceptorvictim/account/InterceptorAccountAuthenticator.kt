package ru.rovkinmax.accountinterceptorvictim.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle

class InterceptorAccountAuthenticator(val context: Context) : AbstractAccountAuthenticator(context) {


    override fun addAccount(response: AccountAuthenticatorResponse?, accountType: String?, authTokenType: String?, requiredFeatures: Array<out String>?, options: Bundle?): Bundle? {
        val bundle = Bundle()
        return bundle
    }

    override fun getAuthToken(response: AccountAuthenticatorResponse?, account: Account?, authTokenType: String, options: Bundle?): Bundle? {
        val am = AccountManager.get(context)
        var token = am.peekAuthToken(account, authTokenType)
        if (!token.isNullOrBlank()) {
            val result = Bundle()
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account!!.name)
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
            result.putString(AccountManager.KEY_AUTHTOKEN, token)
            return result
        } else {
            val bundle = Bundle()
            return bundle
        }
    }


    override fun getAuthTokenLabel(authTokenType: String?): String? = null

    override fun confirmCredentials(response: AccountAuthenticatorResponse?, account: Account?, options: Bundle?): Bundle? = null

    override fun updateCredentials(response: AccountAuthenticatorResponse?, account: Account?, authTokenType: String?, options: Bundle?): Bundle? = null

    override fun hasFeatures(response: AccountAuthenticatorResponse?, account: Account?, features: Array<out String>?): Bundle? = null

    override fun editProperties(response: AccountAuthenticatorResponse?, accountType: String?): Bundle? = null
}