package pl.skyesoftware.newsreader.data.mapper.base

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

interface Mapper<in From, out To> {

    fun mapOrThrow(from: From): To

    fun mapOrSkip(from: From): To?

    fun mapOrThrow(from: List<From?>): List<To>

    fun mapOrSkip(from: List<From?>): List<To>
}