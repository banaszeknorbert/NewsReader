package pl.skyesoftware.newsreader.data.rdp.repository.impl.mixed

import io.reactivex.Completable
import io.reactivex.Observable
import pl.skyesoftware.newsreader.data.rdp.repository.base.Repository
import pl.skyesoftware.newsreader.data.rdp.repository.base.remote.RemoteRepository
import pl.skyesoftware.newsreader.data.rdp.specification.base.MixedSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.base.Specification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class PostsMixedRepository<T>(val storageRepository: Repository<T>,
                              val remoteRepository: RemoteRepository<T>) : Repository<T> {

    override fun add(item: T): Completable = storageRepository.add(item)

    override fun add(items: Iterable<T>): Completable = storageRepository.add(items)

    override fun update(item: T): Completable  = storageRepository.update(item)

    override fun remove(item: T): Completable = storageRepository.remove(item)

    override fun remove(items: Iterable<T>): Completable = storageRepository.remove(items)

    override fun remove(specification: Specification): Completable = storageRepository.remove(specification)

    override fun clear(): Completable = storageRepository.clear()

    override fun query(specification: Specification): Observable<List<T>> =
            (specification as MixedSpecification<T>)
                    .getResults(storageRepository, remoteRepository)

    override fun first(specification: Specification): Observable<T> =
            query(specification)
                    .firstElement()
                    .map { it.first() }
                    .toObservable()!! // inferred
}