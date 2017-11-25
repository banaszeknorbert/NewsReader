package pl.skyesoftware.newsreader.test_util.rest_mock

import android.app.Application
import io.appflate.restmock.RESTMockServerStarter
import io.appflate.restmock.android.AndroidLocalFileParser
import io.appflate.restmock.android.AndroidLogger
import org.robolectric.DefaultTestLifecycle
import org.robolectric.annotation.Config
import org.robolectric.manifest.AndroidManifest
import pl.skyesoftware.newsreader.App
import java.lang.reflect.Method

/**
 * Created by norbertbanaszek on 25.11.2017.
 */
open class RestMockTestLifecycle : DefaultTestLifecycle() {

    override fun createApplication(method: Method, appManifest: AndroidManifest, config: Config): Application {
        val app = super.createApplication(method, appManifest, config)
        RESTMockServerStarter.startSync(AndroidLocalFileParser(app), AndroidLogger())
        return app
    }
}