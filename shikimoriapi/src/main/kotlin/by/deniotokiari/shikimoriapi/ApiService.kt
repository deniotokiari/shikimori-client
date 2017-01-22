package by.deniotokiari.shikimoriapi

import by.deniotokiari.shikimoriapi.models.Anime
import by.deniotokiari.shikimoriapi.models.ApiAccessToken
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    private object Constants {
        const val X_USER_NICK_NAME: String = "X-User-Nickname"
        const val X_USER_API_ACCESS_TOKEN: String = "X-User-Api-Access-Token"

        const val BASE_API_URL: String = "https://shikimori.org/api/"

        const val ACCESS_TOKEN: String = "access_token"
    }

    @GET(Constants.ACCESS_TOKEN)
    fun access_token(@Query("nickname") nickname: String, @Query("password") password: String): Call<ApiAccessToken>

    @GET("animes")
    fun animes(@Query("limit") limit: Int, @Query("page") page: Int): Call<List<Anime>>

    companion object {

        val BASE_URL = "https://shikimori.org"

        fun newInstance(nickname: Lazy<String>, userToken: Lazy<String>): ApiService {
            val httpClient = OkHttpClient()
                    .newBuilder()
                    .authenticator { route, response ->
                        if (route.address().url().url().toString().contains(Constants.ACCESS_TOKEN)) {
                            response.request()
                        } else {
                            response
                                    .request()
                                    .newBuilder()
                                    .header(Constants.X_USER_NICK_NAME, nickname.value)
                                    .header(Constants.X_USER_API_ACCESS_TOKEN, userToken.value)
                                    .build()
                        }
                    }
                    .build()


            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_API_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(httpClient)
                    .build()

            return retrofit.create(ApiService::class.java)
        }

    }

}