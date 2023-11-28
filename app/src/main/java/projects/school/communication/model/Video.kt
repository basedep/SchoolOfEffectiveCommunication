package projects.school.communication.model

import java.io.Serializable

data class Video(
    val title: String,
    val videoUrl: String
) : Serializable