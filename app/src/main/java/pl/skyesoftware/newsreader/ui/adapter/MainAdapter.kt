package pl.skyesoftware.newsreader.ui.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.util.SortedList
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import pl.skyesoftware.newsreader.ui.adapter.delegate.ListAdapterCallback
import pl.skyesoftware.newsreader.ui.adapter.delegate.PostDelegateAdapter
import pl.skyesoftware.newsreader.ui.adapter.delegate.ViewType
import pl.skyesoftware.newsreader.ui.adapter.delegate.ViewTypeDelegateAdapter

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemsSortedList: SortedList<ViewType> = SortedList(ViewType::class.java, ListAdapterCallback(this))
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        delegateAdapters.put(ViewType.ViewTypes.POST, PostDelegateAdapter())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder,
                this.itemsSortedList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun getItemCount(): Int = itemsSortedList.size()

    override fun getItemViewType(position: Int): Int = this.itemsSortedList[position].getViewType()

    fun getItemsList(): SortedList<ViewType> = itemsSortedList

    fun addItems(items: List<ViewType>) {
        itemsSortedList.beginBatchedUpdates()
        itemsSortedList.addAll(items)
        itemsSortedList.endBatchedUpdates()
    }
}
