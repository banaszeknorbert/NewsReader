package pl.skyesoftware.newsreader.data.rdp.repository.base.remote.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.skyesoftware.newsreader.BuildConfig

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

const val LOGGING_ENABLED = true

fun createLoggingHttpClient() =
        OkHttpClient.Builder()
                .addNetworkInterceptor(getRetrofitLoggingInterceptor())
                .build()!! //inferred

fun getRetrofitLoggingInterceptor() = HttpLoggingInterceptor().setLevel(getRetrofitLoggingLevel())!! //inferred


private fun getRetrofitLoggingLevel(): HttpLoggingInterceptor.Level = if (LOGGING_ENABLED and BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE