package com.gads2021_pluralsight.notekeeper

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.gads2021_pluralsight.notekeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        val dm = DataManager()
        // ArrayAdapter is a type of adapter that can be used to fetch in-memory data, such as raise or list
        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            // the next parameter - is the layout to format the selected item in our spinner
            android.R.layout.simple_spinner_item,
            // the last parameter - is the data that we want to retrieve and it is in our DataManager class
            // we just want a collection of all the courses in the HashMap, we can do that by using the 'value' attribute of Map
            // And we need to convert this collection into a list
            dm.courses.values.toList() )
        // set the layout for our dropdown list whenever the spinner is expanded.
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Accessing the spinner
//        val spinnerCourses = findViewById<View>(R.id.spinnerCourses)
        binding.contentLayout.spinnerCourses.adapter = adapterCourses




        //val navController = findNavController(R.id.nav_host_fragment_content_main)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}