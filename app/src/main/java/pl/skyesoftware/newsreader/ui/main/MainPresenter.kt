package pl.skyesoftware.newsreader.ui.main

import com.mateuszkoslacz.moviper.base.presenter.BaseRxPresenter

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class MainPresenter: BaseRxPresenter<MainContract.View, MainContract.Interactor, MainContract.Routing>(), MainContract.Presenter {
    override fun createInteractor(): MainContract.Interactor = MainInteractor()

    override fun createRouting(): MainContract.Routing = MainRouting()

    override fun attachView(view: MainContract.View?) {
        super.attachView(view)
        addSubscription(
                interactor.getPosts()
                        .subscribe({ posts ->
                            view?.setPosts(posts)
                        }, { error ->
                            error.printStackTrace()
                        })
        )
    }
}