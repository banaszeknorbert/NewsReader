package pl.skyesoftware.newsreader.ui.main

import android.app.Activity
import com.hannesdorfmann.mosby.mvp.MvpView
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting
import io.reactivex.Observable
import pl.skyesoftware.newsreader.data.entity.Post

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface MainContract {

    interface Presenter : ViperPresenter<View> {

    }

    interface View : MvpView {
        fun setPosts(posts: List<Post>)
    }

    interface Interactor : ViperRxInteractor {
        fun getPosts() : Observable<List<Post>>
    }

    interface Routing : ViperRxRouting<Activity> {
    }

}