package com.romanvytv.verbis.core.di

import com.romanvytv.verbis.data.network.WordsApi
import org.koin.dsl.module

val apiModule = module {
    single { WordsApi.create() }
}

val dataModule = module {

}

val modules = listOf(
    apiModule
)
