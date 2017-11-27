package pl.skyesoftware.newsreader.data.rdp.specification.impl

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.Observable
import org.junit.Before
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import pl.skyesoftware.newsreader.BuildConfig
import pl.skyesoftware.newsreader.data.entity.dbflow.PostDbModel
import pl.skyesoftware.newsreader.data.rdp.repository.impl.storage.PostsStorageRepository
import pl.skyesoftware.newsreader.data.rdp.specification.base.PostsStorageSpecification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

@Config(constants = BuildConfig::class)
class AllPostsStorageSpecificationTest {

    private val specification = mock<PostsStorageSpecification>()

    @Before
    fun setUp() {
        whenever(specification.getResults())
                .thenReturn(Observable.just(listOf(
                        PostDbModel(0, 1, "title", "body"),
                        PostDbModel(1, 1, "title", "body")
                )))
    }

    @Test
    fun getResults_observableCompletedEmptyListEmitted() {
        specification
                .getResults()
                .test()
                .assertComplete()
                .assertValue(listOf<PostDbModel>(PostDbModel(0, 1, "title", "body"),
                        PostDbModel(1, 1, "title", "body")))
    }

    open fun assertDbContains(expected: Any) {
        val results = SQLite
                .select()
                .from(PostDbModel::class.java)
                .queryList()
        assertThat(results).isEqualTo(expected)
    }
}