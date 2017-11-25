package pl.skyesoftware.newsreader.exception

import android.content.Context
import pl.skyesoftware.newsreader.R

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class MappingExcepiton(model: Any) : BaseNewsReaderException() {

    override val fatal = false

    override val message = "Error while trying to map ${model.javaClass.name} to it's entity \n" +
            "received data: $model"

    override fun getUserMessage(context: Context) =
            context.getString(R.string.error_mapping_entity)
}