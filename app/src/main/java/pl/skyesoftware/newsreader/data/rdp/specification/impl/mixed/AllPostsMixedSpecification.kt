package pl.skyesoftware.newsreader.data.rdp.specification.impl.mixed

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.data.rdp.repository.base.Repository
import pl.skyesoftware.newsreader.data.rdp.repository.base.remote.RemoteRepository
import pl.skyesoftware.newsreader.data.rdp.specification.base.MixedSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.base.RemoteSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.base.Specification
import pl.skyesoftware.newsreader.data.rdp.specification.impl.storage.AllPostsStorageSpecification

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class AllPostsMixedSpecification<Post>(open val storageSpecification: Specification,
                                 open val remoteSpecification: RemoteSpecification<Post>) :
        MixedSpecification<Post> {
    override fun getResults(storageRepository: Repository<Post>, remoteRepository: RemoteRepository<Post>): Observable<List<Post>> {
        val localStream = storageRepository.query(storageSpecification)
                .observeOn(AndroidSchedulers.mainThread())

        val remoteStream = remoteRepository.streamQuery(remoteSpecification)
                .flatMapSingle { incomingEntities ->
                    storageRepository.remove(storageSpecification)
                            .toSingleDefault(incomingEntities)
                            .onErrorReturn {
                                Log.e("AllPostsMixedSpec", it.message)
                                incomingEntities
                            }
                }
                .flatMapSingle { incomingEntities ->
                    storageRepository
                            .add(incomingEntities)
                            .toSingleDefault(incomingEntities)
                            .onErrorReturn {
                                Log.e("AllPostsMixedSpec", it.message)
                                incomingEntities
                            }
                }
                .flatMap { _ -> storageRepository.query(storageSpecification) }
                .observeOn(AndroidSchedulers.mainThread())

        return Observable.mergeDelayError(localStream, remoteStream)
    }
}