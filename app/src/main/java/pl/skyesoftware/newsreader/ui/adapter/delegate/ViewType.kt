package pl.skyesoftware.newsreader.ui.adapter.delegate

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface ViewType {
    fun getViewType(): Int

    object ViewTypes {
        val POST = 0
    }

}