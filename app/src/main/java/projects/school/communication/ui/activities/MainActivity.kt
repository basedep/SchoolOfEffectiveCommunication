package projects.school.communication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import projects.school.communication.R
import projects.school.communication.repository.Repository
import projects.school.communication.viewmodel.CourseViewModel
import projects.school.communication.viewmodel.CourseViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val viewModelProviderFactory  = CourseViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[CourseViewModel::class.java]

        setContentView(R.layout.activity_main)

        //bind navController and BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navigationController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navigationController)

    }

    fun setVisibilityForBottomNavigationView(visibility: Int){
        bottomNavigationView.visibility = visibility
    }

}