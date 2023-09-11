package com.dafinrs.tixcompose

import android.app.Application
import com.dafinrs.tixcompose.di.DomainsModule
import com.dafinrs.tixcompose.di.LocalStorageModule
import com.dafinrs.tixcompose.di.ServicesModule
import com.dafinrs.tixcompose.di.ThirdPartyModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*

class TixComposeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TixComposeApp)
            modules(
                defaultModule,
                ThirdPartyModule().module,
                ServicesModule().module,
                LocalStorageModule().module,
                DomainsModule().module,
            )
        }

    }
}