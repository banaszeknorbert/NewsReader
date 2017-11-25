package pl.skyesoftware.newsreader.data.rdp.specification.impl.storage

import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.Observable
import pl.skyesoftware.newsreader.data.entity.dbflow.PostDbModel
import pl.skyesoftware.newsreader.data.entity.dbflow.PostDbModel_Table
import pl.skyesoftware.newsreader.data.rdp.specification.base.PostsStorageSpecification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class AllPostsStorageSpecification : PostsStorageSpecification {
    override fun getResults(): Observable<List<PostDbModel>> = Observable.fromCallable {
        SQLite
                .select()
                .from(PostDbModel::class.java)
                .orderBy(PostDbModel_Table.id, false)
                .queryList()
    }!! //inferred
}