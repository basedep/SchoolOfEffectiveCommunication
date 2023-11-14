package projects.school.communication.application

import android.app.Application
import projects.school.communication.appwrite.Appwrite

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //initialize instance of Appwrite
            Appwrite().initialize(applicationContext)

    }

}