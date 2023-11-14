package projects.school.communication.appwrite

import android.content.Context
import io.appwrite.Client
import io.appwrite.services.Databases
import projects.school.communication.utils.Constants.BASE_URL
import projects.school.communication.utils.Constants.PROJECT_ID
import kotlin.coroutines.coroutineContext

class Appwrite() {

    companion object{
        private var client: Client? = null
        private var database: Databases? = null
    }

    //initialize client and database
    fun initialize(context: Context) {
        if (client == null) {
            client = Client(context)
                .setEndpoint(BASE_URL)
                .setProject(PROJECT_ID)

            database = Databases(client!!)
        }
    }

    //return the single instance of database
    fun getDatabaseInstance(): Databases {
            return database ?: throw IllegalStateException("Database not initialized")
    }



}

