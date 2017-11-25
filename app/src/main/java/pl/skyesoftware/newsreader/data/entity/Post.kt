package pl.skyesoftware.newsreader.data.entity

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

data class Post(val userId: String?,
                val id: Int = 0,
                val title: String?,
                val body: String?) {

    fun isMissingAnyField() = userId.isNullOrBlank() or title.isNullOrBlank() or body.isNullOrBlank()

}