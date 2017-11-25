package pl.skyesoftware.newsreader.view.main

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class MainPresenter: BaseRxPresenter<MainContract.View, MainContract.Interactor, MainContract.Routing>(), MainContract.Presenter {
    override fun createInteractor(): MainContract.Interactor = MainInteractor()

    override fun createRouting(): MainContract.Routing = MainRouting()

}