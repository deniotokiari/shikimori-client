package by.deniotokiari.shikimoriclient

import android.app.Application
import by.deniotokiari.shikimoriclient.dagger.component.AppComponent
import by.deniotokiari.shikimoriclient.dagger.component.DaggerAppComponent
import by.deniotokiari.shikimoriclient.dagger.module.AppModule

class App : Application() {

    companion object {

        lateinit var appComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}