package pl.skyesoftware.newsreader.data.entity

import android.content.Context
import android.content.Intent
import pl.skyesoftware.newsreader.ui.adapter.delegate.ViewType

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

data class Post(val userId: Int,
                val id: Int = 0,
                val title: String?,
                val body: String?) : ViewType {

    override fun getViewType(): Int = ViewType.ViewTypes.POST

    fun isMissingAnyField() = title.isNullOrBlank() or body.isNullOrBlank()

    fun createIntentFromPost(context: Context, cls: Class<*>) : Intent {
        val intent = Intent(context, cls)
        intent.putExtra(POST_ID_KEY, id)
        intent.putExtra(USER_ID_KEY, userId)
        intent.putExtra(TITLE_KEY, title)
        intent.putExtra(BODY_KEY, body)
        return intent
    }

    companion object {
        val POST_ID_KEY = "postId"
        val USER_ID_KEY = "userId"
        val TITLE_KEY = "title"
        val BODY_KEY = "body"

        fun getPostFromIntent(intent: Intent) : Post {
            return Post(
                    intent.getIntExtra(USER_ID_KEY, 0),
                    intent.getIntExtra(POST_ID_KEY, 0),
                    intent.getStringExtra(TITLE_KEY),
                    intent.getStringExtra(BODY_KEY)
            )
        }
    }



}