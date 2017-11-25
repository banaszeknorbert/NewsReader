package pl.skyesoftware.newsreader.exception

import android.content.Context

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

abstract class BaseNewsReaderException : Exception {

    constructor() : super()
    constructor(throwable: Throwable) : super(throwable)

    abstract val fatal: Boolean

    abstract fun getUserMessage(context: Context): String

    override fun equals(other: Any?): Boolean = other?.let { this::class == other::class } ?: false

}