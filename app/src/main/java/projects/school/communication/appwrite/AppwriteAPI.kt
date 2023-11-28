package projects.school.communication.appwrite

import android.content.Context
import io.appwrite.models.Document
import io.appwrite.models.File
import io.appwrite.models.FileList
import io.appwrite.models.Session
import io.appwrite.models.User
import projects.school.communication.model.Course

interface AppwriteAPI {

    suspend fun getAllCourses(): List<Document<Map<String, Any>>>

    suspend fun searchCourse(query: String): List<Document<Map<String, Any>>>

}