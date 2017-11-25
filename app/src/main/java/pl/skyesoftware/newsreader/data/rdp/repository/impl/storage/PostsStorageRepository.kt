package pl.skyesoftware.newsreader.data.rdp.repository.impl.storage

import android.util.Log
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.data.entity.dbflow.DbFlowDatabase
import pl.skyesoftware.newsreader.data.entity.dbflow.PostDbModel
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

    override fun update(items: Iterable<Post>): Completable =
            Observable
                    .fromIterable(items)
                    .flatMapCompletable { add(it) }
                    .subscribeOn(Schedulers.computation())!! // inferred

    override fun add(items: Iterable<Post>): Completable =
            Observable
                    .fromIterable(items)
                    .flatMapCompletable { add(it) }
                    .subscribeOn(Schedulers.computation())!!

    override fun remove(item: Post): Completable = Completable
            .fromAction { postToPostDbModelMapper.mapOrThrow(item).delete() }
            .subscribeOn(Schedulers.computation())!!

    override fun remove(items: Iterable<Post>): Completable = Observable
            .fromIterable(items)
            .flatMapCompletable { remove(it) }
            .subscribeOn(Schedulers.computation())!! // inferred

    override fun remove(specification: Specification): Completable =
            Single
                    .just(Unit)
                    .observeOn(Schedulers.computation())
                    .flatMap { (specification as PostsStorageSpecification).getResults().firstOrError() }
                    .flatMap { it.delete().toSingleDefault(it) }
                    .toCompletable()
                    .onErrorComplete()!!

    private fun Iterable<PostDbModel>.delete() =
            runInTransaction { forEach { it.delete() } }


    private fun runInTransaction(transactionBody: ((DatabaseWrapper) -> Unit)) =
            Completable.create { emitter ->
                FlowManager
                        .getDatabase(DbFlowDatabase::class.java)
                        .executeTransaction {
                            transactionBody(it)
                            emitter.onComplete()
                        }
            }!!

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


