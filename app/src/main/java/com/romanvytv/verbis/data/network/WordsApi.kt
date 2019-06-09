package com.romanvytv.verbis.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.romanvytv.verbis.data.entities.Word
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://wordsapiv1.p.mashape.com/"

interface WordsApi {

    @GET("words/?random=true")
    fun getRandomWordAsync(): Deferred<Response<Word>>

    @GET("words/{word}")
    fun getWordDetailsAsync(@Path("word") word: String): Deferred<Response<Word>>

    companion object {
        fun create(): WordsApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(getHttpClient())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(WordsApi::class.java)
        }

        private fun getHttpClient() = OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

}