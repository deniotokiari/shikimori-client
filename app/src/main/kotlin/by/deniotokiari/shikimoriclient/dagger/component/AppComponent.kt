package by.deniotokiari.shikimoriclient.dagger.component

import by.deniotokiari.shikimoriclient.activity.LoginActivity
import by.deniotokiari.shikimoriclient.activity.StartActivity
import by.deniotokiari.shikimoriclient.dagger.module.ApiModule
import by.deniotokiari.shikimoriclient.dagger.module.AppModule
import by.deniotokiari.shikimoriclient.fragment.AnimeFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(
        ApiModule::class,
        AppModule::class
))
@Singleton
interface AppComponent {

    fun inject(activity: StartActivity)

    fun inject(activity: LoginActivity)

    fun inject(fragment: AnimeFragment)

}