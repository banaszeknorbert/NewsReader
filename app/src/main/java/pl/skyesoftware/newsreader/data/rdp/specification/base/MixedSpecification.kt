package pl.skyesoftware.newsreader.data.rdp.specification.base

import io.reactivex.Observable
import pl.skyesoftware.newsreader.data.rdp.repository.base.Repository
import pl.skyesoftware.newsreader.data.rdp.repository.base.remote.RemoteRepository

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface MixedSpecification<T> : Specification {

    fun getResults(storageRepository: Repository<T>,
                   remoteRepository: RemoteRepository<T>): Observable<List<T>>

}