package by.deniotokiari.shikimoriclient.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import by.deniotokiari.shikimoriapi.ApiService
import by.deniotokiari.shikimoriapi.models.Anime
import by.deniotokiari.shikimoriclient.App
import by.deniotokiari.shikimoriclient.IImageLoader
import by.deniotokiari.shikimoriclient.R
import by.deniotokiari.shikimoriclient.view.EndlessRecyclerOnScrollListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class AnimeFragment : Fragment() {

    @Inject
    lateinit var api: ApiService

    @Inject
    lateinit var imageLoader: IImageLoader

    private var progress: View? = null
    private var secondaryProgress: View? = null

    private var recycler: RecyclerView? = null
    private lateinit var adapter: AnimeRecyclerViewAdapter

    private val apiCallback: Callback<List<Anime>?> = object : Callback<List<Anime>?> {
        override fun onResponse(call: Call<List<Anime>?>?, response: Response<List<Anime>?>?) {
            setProgressVisibility(View.GONE)
            setSecondaryProgressVisibility(View.GONE)

            response?.body()?.let {
                adapter.update(it)
                adapter.notifyDataSetChanged()
            }
        }

        override fun onFailure(call: Call<List<Anime>?>?, t: Throwable?) {
            setProgressVisibility(View.GONE)
            setSecondaryProgressVisibility(View.GONE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_anime, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress = view?.findViewById(android.R.id.progress)
        secondaryProgress = view?.findViewById(android.R.id.secondaryProgress)

        recycler = view?.findViewById(android.R.id.list) as RecyclerView

        adapter = AnimeRecyclerViewAdapter(context, this, imageLoader)

        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)

        val screenWidthPixels: Int = metrics.widthPixels
        val animeItemWidthPixels: Int = resources.getDimensionPixelSize(R.dimen.ANIME_ITEM_WIDTH)

        recycler?.layoutManager = GridLayoutManager(activity, screenWidthPixels / animeItemWidthPixels)
        recycler?.addOnScrollListener(object : EndlessRecyclerOnScrollListener(recycler?.layoutManager!!) {
            override fun onLoadMore(current_page: Int) {
                setSecondaryProgressVisibility(View.VISIBLE)

                api.animes(20, current_page).enqueue(apiCallback)
            }
        })
        recycler?.adapter = adapter

        api.animes(20, 1).enqueue(apiCallback)
    }

    private fun setProgressVisibility(visibility: Int) {
        progress?.visibility = visibility
    }

    private fun setSecondaryProgressVisibility(visibility: Int) {
        secondaryProgress?.visibility = visibility
    }

    private companion object {

        class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val logo: ImageView = itemView.findViewById(R.id.logo) as ImageView
            val title: TextView = itemView.findViewById(R.id.title) as TextView
            val type: TextView = itemView.findViewById(R.id.type) as TextView
            val year: TextView = itemView.findViewById(R.id.year) as TextView

            fun bind(fragment: Fragment, imageLoader: IImageLoader, anime: Anime) {
                imageLoader.load(fragment, logo, ApiService.BASE_URL + anime.image.preview)

                title.text = anime.name
                type.text = anime.kind
                year.text = anime.getAiredOnYear()
            }

        }

        class AnimeRecyclerViewAdapter(val context: Context, val fragment: Fragment, val imageLoader: IImageLoader) : RecyclerView.Adapter<AnimeViewHolder>() {

            val collection: ArrayList<Anime> by lazy { ArrayList<Anime>() }
            val layoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }

            override fun onBindViewHolder(holder: AnimeViewHolder?, position: Int) {
                holder?.bind(fragment, imageLoader, collection[position])
            }

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AnimeViewHolder {
                return AnimeViewHolder(layoutInflater.inflate(R.layout.adapter_anime, parent, false))
            }

            override fun getItemCount(): Int {
                return collection.size
            }

            fun update(collection: List<Anime>) {
                this.collection.addAll(collection)
            }
        }

    }

}