package com.course.dagger_mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class Fragment1: Fragment() {

    companion object {
        fun newInstance(): Fragment = Fragment1()
    }

    private val presenter by viewModel { injector.fragment1PresenterFactory.create("input") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_1, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.isWorking()
    }
}


class Fragment1Presenter @AssistedInject constructor(helper: Helper, @Assisted private val name: String): ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(name: String): Fragment1Presenter
    }

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