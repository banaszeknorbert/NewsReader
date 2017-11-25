package pl.skyesoftware.newsreader.view.main

import android.app.Activity
import com.hannesdorfmann.mosby.mvp.MvpView
import com.mateuszkoslacz.moviper.iface.interactor.ViperRxInteractor
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter
import com.mateuszkoslacz.moviper.iface.routing.ViperRxRouting
import io.reactivex.Observable

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface MainContract {

    interface Presenter : ViperPresenter<View> {

    }

    interface View : MvpView {

    }

    interface Interactor : ViperRxInteractor {

    }

    interface Routing : ViperRxRouting<Activity> {
    }

}