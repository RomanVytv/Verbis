package com.romanvytv.verbis.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romanvytv.verbis.core.exception.Failure
import org.koin.core.KoinComponent

/**
 * Created by roman.vytvytskyi on 6/12/2019.
 */
abstract class BaseViewModel : ViewModel(), KoinComponent {

	var failure: MutableLiveData<Failure> = MutableLiveData()

	fun handleFailure(failure: Failure?) {
		this.failure.value = failure
	}
}