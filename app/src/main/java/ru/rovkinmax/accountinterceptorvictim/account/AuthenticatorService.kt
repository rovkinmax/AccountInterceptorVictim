package ru.rovkinmax.accountinterceptorvictim.account

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AuthenticatorService : Service() {
    private lateinit var authenticator: InterceptorAccountAuthenticator
    override fun onCreate() {
        super.onCreate()
        authenticator = InterceptorAccountAuthenticator(this)
    }

    override fun onBind(intent: Intent?): IBinder? = authenticator.iBinder
}