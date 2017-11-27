package pl.skyesoftware.newsreader.ui.details

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*
import pl.skyesoftware.newsreader.R
import pl.skyesoftware.newsreader.data.entity.Post
import java.util.*

/**
 * Created by norbertbanaszek on 26.11.2017.
 */

class DetailsActivity : AppCompatActivity() {

    companion object {
        fun create(context: Context, post: Post) {
            context.startActivity(post.createIntentFromPost(context, DetailsActivity::class.java))
        }
    }

    lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        post = Post.getPostFromIntent(intent)
        setContentView(R.layout.activity_details)
        fillView()
    }

    private fun fillView() {
        postId.text = String.format(Locale.getDefault(), getString(R.string.post_id_placeholder), post.id)
        userId.text = String.format(Locale.getDefault(), getString(R.string.user_id_placeholder), post.userId)
        postTitle.text = String.format(Locale.getDefault(), getString(R.string.post_title_placeholder), post.title)
        postBody.text = String.format(Locale.getDefault(), getString(R.string.post_body_placeholder), post.body)
    }
}