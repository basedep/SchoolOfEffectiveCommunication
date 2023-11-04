package projects.school.comunication.model

data class Course(
    val header: String,
    val description: String,
    val category: String,
    val duration: Int,
    val image: Int,
    val progress: Int,
    val isAdded: Boolean,
    val isFavorite: Boolean,
    val videoList: List<Video>,
    val testList: List<Test>,
    val watchedVideos: Int,
    val passedTests: Int
)