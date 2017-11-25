package pl.skyesoftware.newsreader.data.rdp.repository.base.remote.util

import okhttp3.Interceptor

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

class JsonContentAcceptingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?) =
            chain
                    ?.request()
                    ?.newBuilder()
                    ?.addHeader("Accept", "application/json")
                    ?.build()
                    ?.let { chain.proceed(it) }
}