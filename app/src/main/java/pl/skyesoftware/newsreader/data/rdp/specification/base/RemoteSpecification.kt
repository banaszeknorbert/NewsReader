package pl.skyesoftware.newsreader.data.rdp.specification.base

import io.reactivex.Single
import retrofit2.Retrofit

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface RemoteSpecification<T> : Specification {

    fun getResults(retrofit: Retrofit): Single<List<T>>

}