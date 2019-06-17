package com.romanvytv.verbis.core.di

import com.romanvytv.verbis.data.WordsRepository
import com.romanvytv.verbis.data.local.AppDatabase
import com.romanvytv.verbis.data.network.WordsApi
import com.romanvytv.verbis.domain.usecases.GetAllWordsUseCase
import com.romanvytv.verbis.domain.usecases.GetRandomWordUseCase
import com.romanvytv.verbis.domain.usecases.GetTodaysWordUseCase
import com.romanvytv.verbis.domain.usecases.SetFavoriteUseCase
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
	factory { GetRandomWordUseCase(get(), get()) }
	factory { GetAllWordsUseCase(get()) }
	factory { SetFavoriteUseCase(get()) }
	factory { GetTodaysWordUseCase(get(), get()) }
}

val modules = listOf(
	apiModule,
	useCasesModule,
	dataModule
)
