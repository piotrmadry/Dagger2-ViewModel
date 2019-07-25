package com.course.dagger_mvp

import dagger.Component
import dagger.Reusable
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Dao @Inject constructor(){
    fun isWorking() = true
}

@Reusable
class Helper @Inject constructor(){
    fun isWorking() = true
}

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppComponentContributors::class])
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>

    val presenter: Fragment1Presenter
}

interface AppComponentProvider {
    val component: AppComponent
}

class MainApplication : DaggerApplication(), AppComponentProvider {
    override val component by lazy { DaggerAppComponent.factory().create(this) as AppComponent }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component

    override fun onCreate() {
        super.onCreate()
    }
}