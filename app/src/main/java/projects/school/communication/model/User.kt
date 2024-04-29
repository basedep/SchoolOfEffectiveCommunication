package projects.school.communication.model

import androidx.room.PrimaryKey

data class User(
    val id: String? = null,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
)