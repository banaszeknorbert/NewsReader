package pl.skyesoftware.newsreader.data.mapper

import pl.skyesoftware.newsreader.data.entity.Post
import pl.skyesoftware.newsreader.data.entity.dbflow.PostDbModel
import pl.skyesoftware.newsreader.data.mapper.base.BaseMapper

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class PostToPostDbModelMapper : BaseMapper<Post, PostDbModel>() {
    override fun mapOrReturnNull(from: Post): PostDbModel? {
        return PostDbModel(
                id = from.id,
                userId = from.userId!!,
                title = from.title!!,
                body = from.body!!
        )
    }

}