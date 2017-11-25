package pl.skyesoftware.newsreader.data.rdp.specification.impl

import com.google.gson.Gson
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import pl.skyesoftware.newsreader.BuildConfig
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.data.rdp.repository.base.remote.util.BaseRetrofitFactory
import pl.skyesoftware.newsreader.data.rdp.repository.impl.remote.PostsRemoteRepository
import pl.skyesoftware.newsreader.data.rdp.specification.impl.remote.AllPostsRemoteSpecification
import pl.skyesoftware.newsreader.test_util.rest_mock.RestMockRobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

@RunWith(RestMockRobolectricTestRunner::class)
@Config(sdk = intArrayOf(23))
class AllPostsRemoteSpecificationTest {

    val POSTS_ENDPOINT_PATH = "posts"
    lateinit var retrofit: Retrofit

    val allPostsSpecification = AllPostsRemoteSpecification()

    @Before
    fun setUp() {
        mockPostsEndpoint()
        mockRetrofit()
    }

    @Test
    fun repository_streamsApiException_forProperResponse() {
        val specification = allPostsSpecification.getResults(retrofit)
        val testObserver = specification
                .toObservable()
                .test()
        testObserver.assertNoErrors()
        testObserver.assertValue(mutableListOf(Post(userId = "1", id = 0, title = "title", body = "body")))
        testObserver.assertComplete()
    }

    private fun mockRetrofit() {
        retrofit = Retrofit.Builder()
                .baseUrl(RESTMockServer.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun mockPostsEndpoint() {
        val body = Gson().toJson(listOf(Post("1", 0, "title", "body")))
        RESTMockServer.whenGET(RequestMatchers.pathEndsWith(POSTS_ENDPOINT_PATH))
                .thenReturn(MockResponse().setBody(body))
//                .thenReturnFile(200, "posts_response/posts.json")
//                .thenReturnString("[{\"userId\"=1, \"id\"=0, \"title\"=\"title\", \"body\"=\"body\"}]")
//                .thenReturnString(200, "[\n" +
//                        "  {\n" +
//                        "    \"userId\": 1,\n" +
//                        "    \"id\": 1,\n" +
//                        "    \"title\": \"title\",\n" +
//                        "    \"body\": \"body\"\n" +
//                        "  }\n" +
//                        "]")
    }

}