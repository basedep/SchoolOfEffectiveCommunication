package projects.school.communication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import projects.school.communication.R
import projects.school.communication.repository.Repository
import projects.school.communication.viewmodel.HomeViewModel
import projects.school.communication.viewmodel.HomeViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bind navController and BottomNavigationView
        val bottomNavigationView:BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navigationController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navigationController)

        val repository = Repository()
        val viewModelProviderFactory  = HomeViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]

    }
}