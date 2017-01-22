package by.deniotokiari.shikimoriapi.models

class Anime(
        val id: Long,
        val name: String,
        val russian: String,
        val image: Image,
        val url: String,
        val kind: String,
        val status: String,
        val episodes: Int,
        val episodes_aired: Int,
        val aired_on: String,
        val released_on: String,
        val rating: String,
        val english: List<String>,
        val japanese: List<String>,
        val synonyms: List<String>,
        val duration: Int,
        val score: String,
        val description: String,
        val description_html: String,
        val description_source: String,
        val favoured: Boolean,
        val anons: Boolean,
        val ongoing: Boolean,
        val thread_id: Long,
        val topic_id: Long,
        val world_art_id: Long,
        val myanimelist_id: Long,
        val ani_db_id: Long,
        val rates_scores_stats: List<Stat<Int, Int>>,
        val rates_statuses_stats: List<Stat<String, Int>>,
        val updated_at: String,
        val genres: List<Genre>,
        val studios: List<Studio>,
        val videos: List<Video>,
        val screenshots: List<Screenshot>
) {

    fun getAiredOnYear(): String {
        return aired_on.split("-")[0]
    }

}