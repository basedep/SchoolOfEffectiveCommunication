package projects.school.communication.repository

import io.appwrite.Query
import projects.school.communication.appwrite.Appwrite
import projects.school.communication.appwrite.AppwriteAPI
import projects.school.communication.utils.Constants.COLLECTION_COURSE_ID
import projects.school.communication.utils.Constants.DATABASE_ID

class Repository : AppwriteAPI {

    //get our remote database
    private val appwriteDatabase  = Appwrite().getDatabaseInstance()

    override suspend fun getAllCourses() =
            appwriteDatabase.listDocuments(DATABASE_ID, COLLECTION_COURSE_ID).documents

    override suspend fun searchCourse(query: String) =
           appwriteDatabase.listDocuments(
               DATABASE_ID,
               COLLECTION_COURSE_ID,
               listOf(Query.search("header", query))
           ).documents

}