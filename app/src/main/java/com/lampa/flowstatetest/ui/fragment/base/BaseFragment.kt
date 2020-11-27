package com.lampa.flowstatetest.ui.fragment.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lampa.flowstatetest.extensions.showToast

abstract class BaseFragment<T : ViewDataBinding>: Fragment() {

    lateinit var binding: T

    fun showError(errorMessage: String) {
        requireContext().showToast(errorMessage)
    }
}