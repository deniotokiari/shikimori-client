package by.deniotokiari.shikimoriclient.dagger.module

import by.deniotokiari.shikimoriapi.ApiService
import by.deniotokiari.shikimoriclient.Constants
import by.deniotokiari.shikimoriclient.ISharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideShikimoriApi(sharedPreferences: ISharedPreferences): ApiService {
        return ApiService.newInstance(
                lazy { sharedPreferences.get(Constants.Pref.NICK_NAME) },
                lazy { sharedPreferences.get(Constants.Pref.USER_TOKEN) }
        )
    }

}