package cz.pinadani.ukazkakodu

import android.app.Application
import cz.pinadani.ukazkakodu.data.remote.users.remoteModule
import cz.pinadani.ukazkakodu.data.remote.networkModule
import cz.pinadani.ukazkakodu.ui.activity.mainModule
import cz.pinadani.ukazkakodu.ui.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                mainModule, module, remoteModule,
                networkModule
            ))
        }
    }
}