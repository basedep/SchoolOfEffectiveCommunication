package projects.school.communication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import io.appwrite.models.Document
import io.appwrite.models.File
import io.appwrite.models.FileList
import kotlinx.coroutines.launch
import projects.school.communication.model.Course
import projects.school.communication.model.Video
import projects.school.communication.repository.Repository

class CourseViewModel(private val repository: Repository) : ViewModel() {

    val allCourses: MutableLiveData<List<Course>> = MutableLiveData()

    init {
        getAllCourses()
    }

    private fun getAllCourses() = viewModelScope.launch {
            val coursesList = extractData(repository.getAllCourses())
            allCourses.postValue(coursesList)
    }


    //extracting our data from response as model class
    private fun extractData(documents: List<Document<Map<String, Any>>>): List<Course>{
        val gson = Gson()
        val dataList = mutableListOf<Course>()

        for (document in documents){
            val dataMap: Map<String, Any> = document.toMap()
            val json = gson.toJson(dataMap["data"])


            val data: Course = gson.fromJson(json, Course::class.java)

            dataList.add(data)
        }

        return dataList
    }



}