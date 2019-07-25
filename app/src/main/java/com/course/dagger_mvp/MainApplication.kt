package com.course.dagger_mvp

import android.app.Activity
import androidx.fragment.app.Fragment
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Component
import dagger.Module
import dagger.Reusable
import dagger.Subcomponent
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

@AssistedModule
@Module(includes = [AssistedInject_PresenterModule::class])
abstract class PresenterModule

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppComponentContributors::class])
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>

    val presentersComponent: PresentersComponent
}

@Subcomponent(modules = [PresenterModule::class])
interface PresentersComponent {

    val fragment1PresenterFactory: Fragment1Presenter.Factory

    val mainActivityPresenter: MainActivityPresenter
}

interface AppComponentProvider {
    val component: AppComponent
}

class MainApplication : DaggerApplication(), AppComponentProvider {

    override val component by lazy { DaggerAppComponent.factory().create(this) as AppComponent }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component
}

val Fragment.injector get() = (activity!!.application as AppComponentProvider).component.presentersComponent
val Activity.injector get() = (application as AppComponentProvider).component.presentersComponent
