package com.romanvytv.verbis.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * TODO: add class comment
 * <p>
 * Created by roman.vytvytskyi on 6/12/2019.
 */
open class BaseViewModel : ViewModel() {

	private val viewModelJob = SupervisorJob()
	val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

	override fun onCleared() {
		super.onCleared()
		viewModelJob.cancel()
	}
}