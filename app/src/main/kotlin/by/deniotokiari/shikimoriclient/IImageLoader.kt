package by.deniotokiari.shikimoriclient

import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide

interface IImageLoader {

    fun load(fragment: Fragment, view: ImageView, url: String)

    companion object {

        fun newInstance(): IImageLoader {
            return object : IImageLoader {
                override fun load(fragment: Fragment, view: ImageView, url: String) {
                    Glide.with(fragment).load(url).into(view)
                }
            }
        }

    }

}