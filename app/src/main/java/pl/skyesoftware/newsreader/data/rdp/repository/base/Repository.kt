package pl.skyesoftware.newsreader.data.rdp.repository.base

import io.reactivex.Completable
import io.reactivex.Observable
import pl.skyesoftware.newsreader.data.rdp.specification.base.Specification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface Repository<T> {

    fun add(item: T): Completable = throw NotImplementedError()

    fun add(items: Iterable<T>): Completable = throw NotImplementedError()

    fun update(item: T): Completable = throw NotImplementedError()

    fun update(items: Iterable<T>): Completable = throw NotImplementedError()

    fun remove(item: T): Completable = throw NotImplementedError()

    fun remove(items: Iterable<T>): Completable = throw NotImplementedError()

    fun remove(specification: Specification): Completable = throw NotImplementedError()

    fun clear(): Completable = throw NotImplementedError()

    fun query(specification: Specification): Observable<List<T>> = throw NotImplementedError()

    fun first(specification: Specification) = query(specification).map { it.first() }!!

}