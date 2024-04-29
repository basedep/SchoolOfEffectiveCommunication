package projects.school.communication.model

data class UserCourseData(
    val userId: String,
    val courseId: String,
    val testsPassed: Int,
    val videoWatched: Int,
    val courseProgress: Int
)