package com.course.dagger_mvp

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@dagger.Module
abstract class AppComponentContributors {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity
}

@dagger.Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun fragment1(): Fragment1

    @ContributesAndroidInjector
    abstract fun fragment2(): Fragment2
}

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dao: Dao
    @Inject
    lateinit var helper: Helper
    @Inject
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        dao.isWorking()
        helper.isWorking()
        presenter.isWorking()

        fragment_1_button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_activity_container, Fragment1.newInstance())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }

        fragment_2_button.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_activity_container, Fragment2.newInstance())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }
}

class MainActivityPresenter @Inject constructor() {
    fun isWorking() = true
}
