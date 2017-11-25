package pl.skyesoftware.newsreader.data.rdp.repository.impl.storage

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.data.mapper.PostDbModelToPostMapper
import pl.skyesoftware.newsreader.data.mapper.PostToPostDbModelMapper
import pl.skyesoftware.newsreader.data.rdp.repository.base.Repository
import pl.skyesoftware.newsreader.data.rdp.specification.base.PostsStorageSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.base.Specification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class PostsStorageRepository : Repository<Post> {

    val postDbmodelToPostMapper = PostDbModelToPostMapper()
    val postToPostDbModelMapper = PostToPostDbModelMapper()

    override fun add(item: Post): Completable = Completable
            .fromAction { postToPostDbModelMapper.mapOrThrow(item).save() }
            .subscribeOn(Schedulers.computation())!!

    override fun add(items: Iterable<Post>): Completable =
            Observable
                    .fromIterable(items)
                    .flatMapCompletable { add(it) }
                    .subscribeOn(Schedulers.computation())!!

    override fun query(specification: Specification): Observable<List<Post>> =
            (specification as PostsStorageSpecification)
                    .getResults()
                    .map(postDbmodelToPostMapper::mapOrSkip)
                    .subscribeOn(Schedulers.computation())!!

    override fun first(specification: Specification): Observable<Post> =
            query(specification)
                    .firstElement()
                    .map { it.first() }
                    .toObservable()!! // inferred
}


