package pl.skyesoftware.newsreader.data.rdp.specification.impl.remote

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.data.rdp.specification.base.RemoteSpecification
import retrofit2.Retrofit
import retrofit2.http.GET

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class AllPostsRemoteSpecification : RemoteSpecification<Post> {
    override fun getResults(retrofit: Retrofit): Single<List<Post>> =
            retrofit.create(AllPostsApi::class.java).getPostsList()
                    .observeOn(Schedulers.io())


    interface AllPostsApi {

        @GET("posts")
        fun getPostsList() : Single<List<Post>>
    }
}