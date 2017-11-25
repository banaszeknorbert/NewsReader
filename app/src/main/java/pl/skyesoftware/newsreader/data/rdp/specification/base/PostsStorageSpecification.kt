package pl.skyesoftware.newsreader.data.rdp.specification.base

import io.reactivex.Observable
import pl.skyesoftware.newsreader.data.entity.dbflow.PostDbModel

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface PostsStorageSpecification : Specification {

    fun getResults(): Observable<List<PostDbModel>>

}