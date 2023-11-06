package projects.school.communication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "CourseTable"
)
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val header: String,
    val description: String,
    val category: String,
    val duration: Int,
    val imageURL: String,
    val progress: Int,
    val isAdded: Boolean,
    val isFavorite: Boolean,
    val videoList: List<Video>,
    val testList: List<Test>,
    val watchedVideos: Int,
    val passedTests: Int
)