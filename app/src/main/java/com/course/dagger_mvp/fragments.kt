package com.course.dagger_mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


val Fragment.injector get() = (activity!!.application as ComponentProvider).component


class Fragment1: Fragment() {

    @Inject
    lateinit var helper: Helper

    companion object {
        fun newInstance(): Fragment = Fragment1()
    }

    private val presenter by viewModel { injector.presenter }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_1, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)

        helper.isWorking()
        presenter.isWorking()
    }
}

inline fun <reified T : ViewModel> Fragment.viewModel(crossinline provider: () -> T) = viewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = provider() as T
    }
}

class Fragment1Presenter @Inject constructor(helper: Helper): ViewModel() {
    fun isWorking(): Boolean = true
}

class Fragment2: Fragment() {

    @Inject
    lateinit var helper: Helper

    companion object {
        fun newInstance(): Fragment = Fragment2()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        helper.isWorking()
    }
}