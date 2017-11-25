package pl.skyesoftware.newsreader.data.mapper.base

import pl.skyesoftware.newsreader.exception.MappingExcepiton


/**
 * Created by norbertbanaszek on 25.11.2017.
 */


abstract class BaseMapper<in From : Any, To> : Mapper<From, To> {

    abstract protected fun mapOrReturnNull(from: From): To?

    override fun mapOrThrow(from: From): To =
            mapOrReturnNull(from) ?: throw MappingExcepiton(from)

    override fun mapOrSkip(from: From): To? = mapOrReturnNull(from)

    override fun mapOrThrow(from: List<From?>) = map(from, this::mapAndAppendOrThrow)

    override fun mapOrSkip(from: List<From?>) = map(from, this::mapAndAppendOrSkip)

    private fun map(from: List<From?>, mapAndAppendTo: (From, MutableList<To>) -> Unit) =
            with(mutableListOf<To>()) {
                from.filterNotNull().forEach { mapAndAppendTo.invoke(it, this) }
                return@with this
            } as List<To> // it's NOT rendundant, without this it will return a MUTABLE list

    private fun mapAndAppendOrThrow(it: From, to: MutableList<To>) {
        to.add(mapOrThrow(it))
    }

    private fun mapAndAppendOrSkip(it: From, to: MutableList<To>) {
        mapOrReturnNull(it)?.let { to.add(it) }
    }

}