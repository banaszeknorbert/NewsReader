package pl.skyesoftware.newsreader.data.rdp.repository.base.remote

import io.reactivex.Observable
import io.reactivex.Single
import pl.skyesoftware.newsreader.data.rdp.specification.base.RemoteSpecification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface RemoteRepository<T> {
    fun streamQuery(specification: RemoteSpecification<T>) : Observable<List<T>>
}