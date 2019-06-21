package com.romanvytv.verbis.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.romanvytv.verbis.core.exception.Failure
import android.app.Activity
import android.view.inputmethod.InputMethodManager


abstract class BaseFragment : Fragment() {

	abstract fun layoutId(): Int

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(layoutId(), container, false)
	}

	inline fun <reified T : ViewModel> obtainViewModel(body: T.() -> Unit): T {
		val vm = ViewModelProviders.of(this)[T::class.java]
		vm.body()
		return vm
	}

	open fun handleFail(failure: Failure?) = notify(failure.toString())

	fun notify(message: String) =
		Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()

	fun hideKeyboard() {
		val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(view?.windowToken, 0)
	}
}

