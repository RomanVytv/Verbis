package com.romanvytv.verbis.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.romanvytv.verbis.R
import com.romanvytv.verbis.core.exception.Failure
import com.romanvytv.verbis.core.platform.BaseFragment
import com.romanvytv.verbis.data.entities.Word
import kotlinx.android.synthetic.main.fragment_today.*

class TodayWordFragment : BaseFragment() {

	override fun layoutId() = R.layout.fragment_today

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

	}
}