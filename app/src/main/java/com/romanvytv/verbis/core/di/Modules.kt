package com.romanvytv.verbis.core.di

import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.local.AppDatabase
import com.romanvytv.verbis.data.network.WordsApi
import com.romanvytv.verbis.domain.usecases.GetRandomWordUseCase
import com.romanvytv.verbis.search.SearchViewModel
import org.koin.dsl.module

val apiModule = module {
    single { WordsApi.create() }
}

val dataModule = module {
    single { WordsRepository.Network(get()) }
    single { WordsRepository.Local(get()) }
    single { AppDatabase.newInstance() }
}

val useCasesModule = module {
    factory { GetRandomWordUseCase(get()) }
}

val viewModelsModule = module {
    factory { SearchViewModel() }
}

val modules = listOf(
    apiModule,
    useCasesModule,
    viewModelsModule,
    dataModule
)
