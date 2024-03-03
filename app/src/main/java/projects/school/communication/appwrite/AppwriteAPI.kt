package projects.school.communication.appwrite

import android.content.Context
import io.appwrite.ID
import io.appwrite.models.Document
import io.appwrite.models.File
import io.appwrite.models.FileList
import io.appwrite.models.Session
import projects.school.communication.model.Course
import projects.school.communication.model.User

interface AppwriteAPI {

    suspend fun getAllCourses(): List<Document<Map<String, Any>>>

    suspend fun searchCourse(query: String): List<Document<Map<String, Any>>>

    suspend fun onLogIn(email:String, password: String):Session

    suspend fun onRegister(user: User, userID: String)

    suspend fun addUser(user: User, userID: String)

    suspend fun deleteSession(sessionID: String)

}