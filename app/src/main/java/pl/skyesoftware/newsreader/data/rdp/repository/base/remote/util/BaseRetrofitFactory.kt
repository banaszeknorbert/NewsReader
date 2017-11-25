package pl.skyesoftware.newsreader.data.rdp.repository.base.remote.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by norbertbanaszek on 25.11.2017.
 */

open class BaseRetrofitFactory(private val url: String) {


    open fun createRetrofitInstance() =
            Retrofit.Builder()
                    .baseUrl(url)
                    .client(createOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()!!//inferred

    private fun createOkHttpClient() =
            OkHttpClient.Builder()
                    .addInterceptor(JsonContentAcceptingInterceptor())
                    .addNetworkInterceptor(getRetrofitLoggingInterceptor())
                    .build()!!//inferred
}