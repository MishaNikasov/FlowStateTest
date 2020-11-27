package com.lampa.flowstatetest.ui.fragment

import androidx.fragment.app.Fragment
import com.lampa.flowstatetest.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment: Fragment() {
    fun showError(errorMessage: String) {
        requireContext().showToast(errorMessage)
    }
}