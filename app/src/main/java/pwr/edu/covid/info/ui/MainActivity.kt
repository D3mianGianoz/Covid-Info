package pwr.edu.covid.info.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import pwr.edu.covid.info.R
import pwr.edu.covid.info.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var myBottomNav: BottomNavigationView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = this.findNavController(R.id.nav_host_fragment)
        // Set up the bottom navigation
        myBottomNav = binding.bottomNavigation
        setupWithNavController(myBottomNav, navController)
        // Set up the appBar navigation
//        appBarConfiguration = AppBarConfiguration(navGraph = navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()

        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, destination: NavDestination, _: Bundle? ->
            when (destination.id) {
                R.id.welcomeFragment -> {
                    myBottomNav.visibility = View.GONE
                }
                else -> {
                    myBottomNav.visibility = View.VISIBLE
                }
            }
//                nc.graph.startDestination -> {
//                    myBottomNav.visibility = View.VISIBLE
//                }
        }
    }
}
