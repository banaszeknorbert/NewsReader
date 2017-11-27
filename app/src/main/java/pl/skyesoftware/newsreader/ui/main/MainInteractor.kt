package pl.skyesoftware.newsreader.ui.main

import com.mateuszkoslacz.moviper.base.interactor.BaseRxInteractor
import io.reactivex.Observable
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.data.rdp.repository.impl.mixed.PostsMixedRepository
import pl.skyesoftware.newsreader.data.rdp.repository.impl.remote.PostsRemoteRepository
import pl.skyesoftware.newsreader.data.rdp.repository.impl.storage.PostsStorageRepository
import pl.skyesoftware.newsreader.data.rdp.specification.impl.mixed.AllPostsMixedSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.impl.remote.AllPostsRemoteSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.impl.storage.AllPostsStorageSpecification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class MainInteractor : BaseRxInteractor(), MainContract.Interactor {
    override fun getPosts(): Observable<List<Post>> =
            PostsMixedRepository(PostsStorageRepository(), PostsRemoteRepository())
                    .query(AllPostsMixedSpecification(
                            AllPostsStorageSpecification(), AllPostsRemoteSpecification()))

}