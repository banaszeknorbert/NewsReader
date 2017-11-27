package pl.skyesoftware.newsreader.ui.adapter.delegate

import android.support.v7.widget.util.SortedListAdapterCallback
import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.ui.adapter.MainAdapter

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class ListAdapterCallback(listAdapter: MainAdapter) : SortedListAdapterCallback<ViewType>(listAdapter) {
    override fun areItemsTheSame(item1: ViewType?, item2: ViewType?): Boolean {
        if (item1 is Post && item2 is Post) {
            return item1.id == item2.id
        }
        return false
    }

    override fun areContentsTheSame(oldItem: ViewType?, newItem: ViewType?): Boolean {
        if (oldItem is Post && newItem is Post) {
            return oldItem.title == newItem.title
        }
        return false
    }

    override fun compare(oldItem: ViewType?, newItem: ViewType?): Int {
        if (oldItem is Post && newItem is Post) {
            return newItem.id.compareTo(oldItem.id)
        }
        return 0
    }

}