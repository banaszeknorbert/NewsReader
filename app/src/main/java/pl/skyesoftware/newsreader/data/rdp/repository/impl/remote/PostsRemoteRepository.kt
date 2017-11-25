package pl.skyesoftware.newsreader.data.rdp.repository.impl.remote

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.data.rdp.repository.base.remote.RemoteRepository
import pl.skyesoftware.newsreader.data.rdp.repository.base.remote.util.BaseRetrofitFactory
import pl.skyesoftware.newsreader.data.rdp.specification.base.RemoteSpecification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class PostsRemoteRepository : RemoteRepository<Post> {
    var baseRetrofitFactory = BaseRetrofitFactory("https://jsonplaceholder.typicode.com/")

    override fun streamQuery(specification: RemoteSpecification<Post>): Observable<List<Post>> =
            Observable.just(Unit)
                    .observeOn(Schedulers.io())
                    .flatMapSingle { specification.getResults(baseRetrofitFactory.createRetrofitInstance()) }
                    .observeOn(AndroidSchedulers.mainThread())

}
