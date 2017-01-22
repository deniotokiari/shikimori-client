package by.deniotokiari.shikimoriapi.models

data class Video(
        val id: Long,
        val url: String,
        val image_url: String,
        val player_url: String,
        val name: String,
        val kind: String,
        val hosting: String
)