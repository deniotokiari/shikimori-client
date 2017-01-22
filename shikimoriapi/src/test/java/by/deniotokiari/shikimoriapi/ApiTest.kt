package by.deniotokiari.shikimoriapi

import android.os.Build
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.mock.MockRetrofit

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.KITKAT))
class ApiTest {

    private lateinit var server: MockRetrofit

    @Before
    fun setUp() {

    }

    @Test
    fun access_token() {

    }

}