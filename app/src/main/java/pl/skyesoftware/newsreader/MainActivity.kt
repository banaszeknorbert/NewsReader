package pl.skyesoftware.newsreader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import pl.skyesoftware.newsreader.data.rdp.repository.impl.remote.PostsRemoteRepository
import pl.skyesoftware.newsreader.data.rdp.repository.impl.storage.PostsStorageRepository
import pl.skyesoftware.newsreader.data.rdp.specification.impl.remote.AllPostsRemoteSpecification
import pl.skyesoftware.newsreader.data.rdp.specification.impl.storage.AllPostsStorageSpecification

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FlowManager.init(FlowConfig.Builder(this).build())
        PostsRemoteRepository().streamQuery(AllPostsRemoteSpecification())
                .subscribe({ list ->
                    Log.e("MainActivity", "Received list: " + list.size)
                }, { error ->
                    Log.e("MainActivity", error.message)
                })

        PostsStorageRepository().query(AllPostsStorageSpecification())
                .subscribe({ list ->
                    Log.e("MainActivity", "Received list from storage: " + list.size)
                }, { error ->
                    Log.e("MainActivity", error.message)
                })
    }
}
