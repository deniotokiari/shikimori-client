package by.deniotokiari.shikimoriclient.dagger.module

import android.content.Context
import by.deniotokiari.shikimoriclient.IDateFormatter
import by.deniotokiari.shikimoriclient.IImageLoader
import by.deniotokiari.shikimoriclient.ISharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): ISharedPreferences {
        return ISharedPreferences.newInstance(context)
    }

    @Provides
    @Singleton
    fun provideImageLoader(): IImageLoader {
        return IImageLoader.newInstance()
    }

    @Provides
    @Singleton
    fun provideDateFormatter(): IDateFormatter {
        return IDateFormatter.newInstance()
    }

}