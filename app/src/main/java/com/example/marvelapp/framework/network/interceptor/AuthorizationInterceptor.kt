package com.example.marvelapp.framework.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

class AuthorizationInterceptor(
    private val publicKey: String,
    private val privateKey: String,
    private val calendar: Calendar
) : Interceptor {

    companion object {
        private const val QUERY_PARAMETER_TS = "ts"
        private const val QUERY_PARAMETER_API_KEY = "api_key"
        private const val QUERY_PARAMETER_HASH = "hash"
    }

    @Suppress("MagicNumber")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val urlRequest = request.url

        val ts = (calendar.timeInMillis / 1000).toString()
        val hash = "$ts$publicKey$privateKey".md5()
        val newUrl = urlRequest.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_TS, ts)
            .addQueryParameter(QUERY_PARAMETER_API_KEY, publicKey)
            .addQueryParameter(QUERY_PARAMETER_HASH, hash)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

    @Suppress("MagicNumber")
    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(
            1,
            md.digest(toByteArray())
        ).toString(16).padStart(32, '0')
    }


}