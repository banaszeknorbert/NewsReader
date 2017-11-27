package pl.skyesoftware.newsreader.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.mateuszkoslacz.moviper.base.view.activity.ViperActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.skyesoftware.newsreader.R
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.ui.adapter.MainAdapter

class MainActivity : ViperActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

    private val mainAdapter by lazy { MainAdapter() }

    override fun createPresenter(): MainContract.Presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    override fun setPosts(posts: List<Post>) {
        mainAdapter.addItems(posts)
    }

    private fun setupRecyclerView() {
        postsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
    }
}
