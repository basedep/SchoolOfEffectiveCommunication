package projects.school.communication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import projects.school.communication.repository.Repository

//creating factory method to pass the repository as a parameter
class CourseViewModelProviderFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CourseViewModel(repository) as T
    }
}