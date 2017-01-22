package by.deniotokiari.shikimoriclient.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import by.deniotokiari.shikimoriclient.R
import by.deniotokiari.shikimoriclient.fragment.AnimeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, AnimeFragment())
                .commit()
    }

}
