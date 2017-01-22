package by.deniotokiari.shikimoriclient.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.deniotokiari.shikimoriclient.App
import by.deniotokiari.shikimoriclient.Constants
import by.deniotokiari.shikimoriclient.ISharedPreferences
import javax.inject.Inject

class StartActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: ISharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)

        if (sharedPreferences.get(Constants.Pref.USER_TOKEN, "").isEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }

        finish()
    }

}