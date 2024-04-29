package projects.school.communication.repository

import io.appwrite.ID
import io.appwrite.Query
import io.appwrite.models.Document
import io.appwrite.models.Session
import projects.school.communication.appwrite.Appwrite
import projects.school.communication.appwrite.AppwriteAPI
import projects.school.communication.model.User
import projects.school.communication.model.UserCourseData
import projects.school.communication.utils.Constants.COLLECTION_COURSE_ID
import projects.school.communication.utils.Constants.COLLECTION_USERS_DATA_ID
import projects.school.communication.utils.Constants.COLLECTION_USERS_ID
import projects.school.communication.utils.Constants.DATABASE_ID

class Repository : AppwriteAPI {

    //get our remote database
    private val appwriteDatabase = Appwrite().getDatabaseInstance()
    private val appwriteAccount = Appwrite().getAccountInstance()

    override suspend fun getAllCourses() =
        appwriteDatabase.listDocuments(DATABASE_ID, COLLECTION_COURSE_ID).documents


    override suspend fun searchCourse(query: String) =
        appwriteDatabase.listDocuments(
            DATABASE_ID,
            COLLECTION_COURSE_ID,
            listOf(Query.search("header", query))
        ).documents

    override suspend fun getCourseById(courseId: String) =
        appwriteDatabase.getDocument(
            DATABASE_ID,
            COLLECTION_COURSE_ID,
            courseId
        )


    override suspend fun getListOfUserCourses(userId: String)=
        appwriteDatabase.listDocuments(
            DATABASE_ID,
            COLLECTION_USERS_DATA_ID,
            listOf(Query.search("userId", userId))
        ).documents


    override suspend fun onLogIn(email: String, password: String): Session =
        appwriteAccount.createEmailSession(email, password)


    override suspend fun onRegister(user: User, userID: String) {
        appwriteAccount.create(userID, user.email, user.password, user.fullName)
    }

    suspend fun getUserData(userDocumentId: String) =
        appwriteDatabase.getDocument(DATABASE_ID, COLLECTION_USERS_ID, userDocumentId).data


    override suspend fun addUser(user: User, userID: String) {
        appwriteDatabase.createDocument(DATABASE_ID, COLLECTION_USERS_ID, userID, user)
    }

    override suspend fun deleteSession(sessionID: String) {
        appwriteAccount.deleteSession(sessionID)
    }

    override suspend fun addCourse(data: UserCourseData) {
        appwriteDatabase.createDocument(DATABASE_ID, COLLECTION_USERS_DATA_ID, ID.unique(), data)
    }

    override suspend fun getAddedCourseData(courseId: String, userId: String): List<Document<Map<String, Any>>> =
        appwriteDatabase.listDocuments(
            DATABASE_ID,
            COLLECTION_USERS_DATA_ID,
            listOf(Query.search("courseId", courseId), Query.search("userId", userId))
        ).documents


}