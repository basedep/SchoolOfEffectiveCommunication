package projects.school.communication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.Document
import io.appwrite.models.Session
import kotlinx.coroutines.launch
import projects.school.communication.model.Course
import projects.school.communication.model.User
import projects.school.communication.repository.Repository
import java.lang.Exception

class CourseViewModel(private val repository: Repository) : ViewModel() {

    val allCourses: MutableLiveData<List<Course>> = MutableLiveData()

    val searchCourse: MutableLiveData<List<Course>> = MutableLiveData()

    var isRegisterSuccessful: MutableLiveData<Boolean> = MutableLiveData()

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

    fun onLogIn(user: User) = viewModelScope.launch {
        try {
            val session = repository.onLogIn(user)
            session.id
        }catch (e: Exception){

        }
    }



    fun onRegister(user: User, userID: String) = viewModelScope.launch {
        try {
            repository.onRegister(user, userID)
            repository.addUser(user, userID)
            isRegisterSuccessful.value = true
        } catch (e: AppwriteException) {
            Log.d("communication", "onRegister: $e, isRegisterCastException ${isRegisterSuccessful.value}")
            isRegisterSuccessful.value = false
        }
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