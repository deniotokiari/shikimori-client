package by.deniotokiari.shikimoriclient.dagger.module

import by.deniotokiari.shikimoriclient.IDateFormatter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DateFormatterModule {

    @Provides
    @Singleton
    fun provideDateFormatter(): IDateFormatter {
        return IDateFormatter.newInstance()
    }

}