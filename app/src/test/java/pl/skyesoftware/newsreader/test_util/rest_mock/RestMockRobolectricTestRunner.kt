package pl.skyesoftware.newsreader.test_util.rest_mock

import org.junit.runners.model.InitializationError
import org.robolectric.RobolectricTestRunner
import org.robolectric.TestLifecycle

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

open class RestMockRobolectricTestRunner @Throws(InitializationError::class)
constructor(klass: Class<*>) : RobolectricTestRunner(klass) {

    public override fun getTestLifecycleClass(): Class<out TestLifecycle<*>> {
        return RestMockTestLifecycle::class.java
    }
}