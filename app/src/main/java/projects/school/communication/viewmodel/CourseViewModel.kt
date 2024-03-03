package projects.school.communication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.gson
import io.appwrite.models.Document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import projects.school.communication.appwrite.Appwrite
import projects.school.communication.model.Course
import projects.school.communication.model.User
import projects.school.communication.repository.Repository

class CourseViewModel(private val repository: Repository) : ViewModel() {

    val allCourses: MutableLiveData<List<Course>> = MutableLiveData()

    val searchCourse: MutableLiveData<List<Course>> = MutableLiveData()

    // изначально заполнить всеми курсами
    init {
        getAllCourses()
        searchAllCourses()
    }

    private fun getAllCourses() = viewModelScope.launch {
        val coursesList = extractData(repository.getAllCourses())
        allCourses.postValue(coursesList)
    }

    fun searchCourse(query: String) = viewModelScope.launch {
        val response = extractData(repository.searchCourse(query))
        searchCourse.postValue(response)
    }

    //вызвать если строка поиска пуста
    fun searchAllCourses() = viewModelScope.launch {
        val response = extractData(repository.getAllCourses())
        searchCourse.postValue(response)
    }

    fun loginUser(email: String, password: String) = viewModelScope.async {
        repository.onLogIn(email, password)
    }

    fun deleteSession(sessionID: String) = viewModelScope.launch{
        repository.deleteSession(sessionID)
    }



    fun registerUser(user: User, userID: String) = viewModelScope.launch {
        try {
            repository.onRegister(user, userID)
            repository.addUser(user, userID)
        } catch (e: Exception) {
            this.cancel()
        }

    }


    fun getUserData(userID: String) = viewModelScope.async {
        val gson = Gson()
        val result = repository.getUserData(userID)
        val data: User = gson.fromJson(gson.toJson(result), User::class.java)
        data
    }

    //extracting our data from response as model class
    private fun extractData(documents: List<Document<Map<String, Any>>>): List<Course> {
        val gson = Gson()
        val dataList = mutableListOf<Course>()

        for (document in documents) {
            val dataMap: Map<String, Any> = document.toMap()
            val json = gson.toJson(dataMap["data"])

            val data: Course = gson.fromJson(json, Course::class.java)

            dataList.add(data)
        }

        return dataList
    }


}