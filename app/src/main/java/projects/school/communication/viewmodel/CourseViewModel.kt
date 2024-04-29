package projects.school.communication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import io.appwrite.extensions.gson
import io.appwrite.extensions.toJson
import io.appwrite.models.Document
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.json.JSONArray
import projects.school.communication.model.Course
import projects.school.communication.model.User
import projects.school.communication.model.UserCourseData
import projects.school.communication.repository.Repository
import projects.school.communication.utils.SessionUtil
import kotlin.reflect.typeOf

class CourseViewModel(private val repository: Repository) : ViewModel() {

    val allCourses: MutableLiveData<List<Course>> = MutableLiveData()

    val searchCourse: MutableLiveData<List<Course>> = MutableLiveData()

    val allUserAddedCourses: MutableLiveData<List<Course>> = MutableLiveData()

    // изначально заполнить всеми курсами
    init {
        getAllCourses()
        searchAllCourses()
    }

    private fun getAllCourses() = viewModelScope.launch {
        val coursesList = extractData(repository.getAllCourses())
        allCourses.postValue(coursesList)
    }

    //достает из таблицы данные курсов пользователя, ищет их в другой табице и постит курсы в liveData
    fun getAllUserAddedCourses(userId: String) = viewModelScope.launch {
        val coursesData = repository.getListOfUserCourses(userId)
        val courses = mutableListOf<Course>()
        val gson = Gson()

        for (document in coursesData) {
            val dataMap: Map<String, Any> = document.toMap()
            val json = gson.toJson(dataMap["data"])
            val data: String = gson.fromJson(json, UserCourseData::class.java).courseId
            val course = extractData(listOf(repository.getCourseById(data)))[0]
            courses.add(course)
        }
        allUserAddedCourses.postValue(courses)
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


    fun addUserCourseData(userData: UserCourseData) = viewModelScope.launch {
       val isCourseExist: List<Document<Map<String, Any>>>  = repository.getAddedCourseData(userData.courseId, userData.userId)

        if (isCourseExist.isEmpty())
            repository.addCourse(userData)
        else
            this.cancel()
    }


    //получаем данные по курсу во фрагменте курса
    fun getUserCourseData(courseId: String, userId: String) = viewModelScope.async {
        val gson = Gson()
        val result  = repository.getAddedCourseData(courseId, userId)
        var data: UserCourseData? = null
        if(result.isNotEmpty())
            data = gson.fromJson(gson.toJson(result[0].toMap()["data"]), UserCourseData::class.java)
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
            data.id = document.id

            dataList.add(data)
        }

        return dataList
    }


}