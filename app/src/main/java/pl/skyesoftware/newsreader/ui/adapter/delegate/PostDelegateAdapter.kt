package pl.skyesoftware.newsreader.ui.adapter.delegate

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import pl.skyesoftware.newsreader.R
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.extensions.inflate
import pl.skyesoftware.newsreader.ui.details.DetailsActivity

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class PostDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = PostViewHolder(parent, R.layout.viewholder_post)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PostViewHolder
        holder.fillView(item as Post)
        holder.itemView.setOnClickListener {
            DetailsActivity.create(holder.itemView.context, item)
        }
    }

    class PostViewHolder(parent: ViewGroup, @LayoutRes private var layoutId: Int) :
            RecyclerView.ViewHolder(parent.inflate(R.layout.viewholder_post)) {

        fun fillView(post: Post) {
            itemView.findViewById<TextView>(R.id.postTitle).text = post.title
            itemView.findViewById<TextView>(R.id.postBody).text = post.body
        }
    }
}