package projects.school.communication.appwrite

import io.appwrite.models.Document
import io.appwrite.models.Session
import projects.school.communication.model.User
import projects.school.communication.model.UserCourseData

interface AppwriteAPI {

    suspend fun getAllCourses(): List<Document<Map<String, Any>>>

    suspend fun searchCourse(query: String): List<Document<Map<String, Any>>>

    suspend fun getCourseById(courseId: String): Document<Map<String, Any>>

    suspend fun getListOfUserCourses(userId: String): List<Document<Map<String, Any>>>

    suspend fun getAddedCourseData(courseId: String, userId: String): List<Document<Map<String, Any>>>

    suspend fun onLogIn(email:String, password: String):Session

    suspend fun onRegister(user: User, userID: String)

    suspend fun addUser(user: User, userID: String)

    suspend fun deleteSession(sessionID: String)

    suspend fun addCourse(data: UserCourseData)

}