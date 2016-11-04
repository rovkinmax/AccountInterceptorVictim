package ru.rovkinmax.accountinterceptorvictim

import android.accounts.AccountManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.rovkinmax.accountinterceptorvictim.account.AuthUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPutToken.setOnClickListener { putToken(); invalidateAccounts() }
    }

    private fun putToken() {
        val account = AuthUtil.getOrCreateAccount(this, etLogin.text.toString())
        AuthUtil.putToken(this, account, etToken.text.toString())
    }

    private fun invalidateAccounts() {
        val am = AccountManager.get(this)
        tvAccounts.text = buildString {
            am.getAccountsByType(BuildConfig.ACCOUNT_TYPE).forEach { account ->
                try {
                    append("${account?.name}\n${account?.type}")
                    append("\n${AuthUtil.getToken(this@MainActivity)}")
                    append("\n\n")
                } catch(e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}