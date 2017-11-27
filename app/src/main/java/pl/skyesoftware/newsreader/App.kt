package pl.skyesoftware.newsreader

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import android.util.Log
import com.mateuszkoslacz.moviper.presenterbus.Config
import com.mateuszkoslacz.moviper.presenterbus.Moviper
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class App : MultiDexApplication() {

    @Suppress("CatchRuntimeException")
    override fun attachBaseContext(base: Context?) {
        try {
            super.attachBaseContext(base)
        } catch (ignored: RuntimeException) {
            if (isItRobolectricTest().not()) throw ignored // Re-throw if this does not seem to be triggered by Robolectric.

            else Log.e("NewsReader", "If you're reading this something went terribly wrong. " +
                            "Your device setup seems to be a Robolectric test suite")
        }
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(FlowConfig.Builder(this).build())
        Moviper.getInstance().setConfig(Config.Builder()
                .withPresenterAccessUtilEnabled(true) // plain IPC
                .withInstancePresentersEnabled(false) // acces to specific presenters
                .build())
    }
}

fun isItRobolectricTest(): Boolean {
    var isUnderUnitTest: Boolean
    try {
        val robolectric = Class.forName("org.robolectric.Robolectric")
        isUnderUnitTest = robolectric != null
    } catch (e: ClassNotFoundException) {
        isUnderUnitTest = false
    }
    return isUnderUnitTest
}
