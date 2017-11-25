package pl.skyesoftware.newsreader.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mateuszkoslacz.moviper.base.view.activity.ViperActivity
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import pl.skyesoftware.newsreader.R
import pl.skyesoftware.newsreader.data.rdp.repository.impl.mixed.PostsMixedRepository
import pl.skyesoftware.newsreader.data.rdp.repository.impl.remote.PostsRemoteRepository
import pl.skyesoftware.newsreader.data.rdp.repository.impl.storage.PostsStorageRepository
import pl.skyesoftware.newsreader.data.rdp.specification.impl.mixed.AllPostsMixedSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.impl.remote.AllPostsRemoteSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.impl.storage.AllPostsStorageSpecification

class MainActivity : ViperActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    override fun createPresenter(): MainContract.Presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FlowManager.init(FlowConfig.Builder(this).build())
        PostsMixedRepository(PostsStorageRepository(), PostsRemoteRepository())
                .query(AllPostsMixedSpecification(AllPostsStorageSpecification(), AllPostsRemoteSpecification()))
                .subscribe({ list ->
                    Log.e("MainActivity", "Received list: " + list.size)
                }, { error ->
                    Log.e("MainActivity", error.message)
                })
    }
}
