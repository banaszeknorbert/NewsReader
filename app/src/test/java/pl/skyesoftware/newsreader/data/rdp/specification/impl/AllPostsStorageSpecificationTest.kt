package pl.skyesoftware.newsreader.data.rdp.specification.impl

import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import pl.skyesoftware.newsreader.BuildConfig
import pl.skyesoftware.newsreader.data.entity.dbflow.PostDbModel
import pl.skyesoftware.newsreader.data.rdp.specification.impl.storage.AllPostsStorageSpecification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class AllPostsStorageSpecificationTest {

    @Before
    fun setUp() {
        FlowManager.init(FlowConfig.Builder(RuntimeEnvironment.application).build())
    }

    @Test
    fun getResults_observableCompletedEmptyListEmitted() {
        AllPostsStorageSpecification()
                .getResults()
                .test()
                .assertComplete()
                .assertValue(listOf<PostDbModel>())
    }
}