package by.deniotokiari.shikimoriclient.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import by.deniotokiari.shikimoriapi.ApiService
import by.deniotokiari.shikimoriapi.models.ApiAccessToken
import by.deniotokiari.shikimoriclient.App
import by.deniotokiari.shikimoriclient.Constants
import by.deniotokiari.shikimoriclient.ISharedPreferences
import by.deniotokiari.shikimoriclient.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var api: ApiService

    @Inject
    lateinit var prefs: ISharedPreferences

    private lateinit var progress: View

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        progress = findViewById(android.R.id.progress)

        val nickNameEditText = findViewById(R.id.nick_name) as EditText
        val passwordEditText = findViewById(R.id.password) as EditText

        fun isValid(): Boolean {
            return nickNameEditText.text != null && passwordEditText.text != null && !nickNameEditText.text.isEmpty() && !passwordEditText.text.isEmpty()
        }

        findViewById(R.id.login).setOnClickListener {
            if (isValid()) {
                progress.visibility = View.VISIBLE

                api.access_token(nickNameEditText.text.toString(), passwordEditText.text.toString()).enqueue(object : Callback<ApiAccessToken?> {
                    override fun onFailure(call: Call<ApiAccessToken?>?, t: Throwable?) {
                        progress.visibility = View.GONE

                        t?.message?.let {
                            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onResponse(call: Call<ApiAccessToken?>?, response: Response<ApiAccessToken?>?) {
                        progress.visibility = View.GONE

                        response?.body()?.api_access_token?.let { token ->
                            prefs.put(Constants.Pref.USER_TOKEN, token)

                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))

                            finish()
                        }
                    }
                })
            } else {
                Toast.makeText(this, R.string.WRONG_CREDENTIALS, Toast.LENGTH_LONG).show()
            }
        }
    }

}