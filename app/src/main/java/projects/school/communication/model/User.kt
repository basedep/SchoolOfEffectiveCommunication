package projects.school.communication.model

import androidx.room.PrimaryKey

data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val courses: List<Course>?
)