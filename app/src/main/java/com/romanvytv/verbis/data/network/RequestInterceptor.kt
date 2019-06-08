package com.romanvytv.verbis.data.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor class for setting of the headers for every request
 */

class RequestInterceptor : Interceptor {

    //TODO: hide api key
    private val WORDS_API_KEY = "b9a9ecf645msh55f2b66a4ad8c7dp15e7acjsn3e2240937674"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
            .addHeader("X-RapidAPI-Key", WORDS_API_KEY)
            .build()
        return chain.proceed(request)
    }
}