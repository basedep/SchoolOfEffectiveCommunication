package projects.school.communication.appwrite

import android.content.Context
import io.appwrite.Client
import io.appwrite.models.User
import io.appwrite.services.Account
import io.appwrite.services.Databases
import io.appwrite.services.Storage
import projects.school.communication.utils.Constants.BASE_URL
import projects.school.communication.utils.Constants.PROJECT_ID

class Appwrite() {

    companion object{
        private var client: Client? = null
        private var database: Databases? = null
        private var account: Account? = null
        private var storage: Storage? = null
    }

    //initialize client and database
    fun initialize(context: Context) {
        if (client == null) {
            client = Client(context)
                .setEndpoint(BASE_URL)
                .setProject(PROJECT_ID)


            database = Databases(client!!)
            account = Account(client!!)
            storage = Storage(client!!)
        }
    }

    //return the single instance of database
    fun getDatabaseInstance(): Databases {
            return database ?: throw IllegalStateException("Database not initialized")
    }

    fun getAccountInstance(): Account {
        return account ?: throw IllegalStateException("Account not initialized")
    }

}

