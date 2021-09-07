package com.gads2021_pluralsight.notekeeper

import android.os.Bundle
import android.provider.SyncStateContract
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
import com.gads2021_pluralsight.notekeeper.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {

    private var notePosition = POSITION_NOT_SET

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding   // binding for the activity_main.xml layout
    private lateinit var includedLayoutBinding: ContentMainBinding   // binding for the content_main.xml layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        includedLayoutBinding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        // val dm = DataManager() - since this has become object, we can no longer call its constructor,
        // instead, we call the name directly

        // ArrayAdapter is a type of adapter that can be used to fetch in-memory data, such as raise or list
        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            // the next parameter - is the layout to format the selected item in our spinner
            android.R.layout.simple_spinner_item,
            // the last parameter - is the data that we want to retrieve and it is in our DataManager class
            // we just want a collection of all the courses in the HashMap, we can do that by using the 'value' attribute of Map
            // And we need to convert this collection into a list
            // we call the class by its name since it has been made to be object - singleton swaggs
            DataManager.courses.values.toList() )
        // set the layout for our dropdown list whenever the spinner is expanded.
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Accessing the spinner's adapter attribute and assigning it with the created above adapter
        includedLayoutBinding.spinnerCourses.adapter = adapterCourses

        // getting the position of the selected note before launching this activity
        // if the selected position is not available, we want to return POSITION_NOT_SET
        notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        // as long as position selected is available, we want to go ahead and display the selected note
        if(notePosition != POSITION_NOT_SET)
            displayNote()   // then we are aware that the position exists if the above if statement is true, then we run the function.


        //val navController = findNavController(R.id.nav_host_fragment_content_main)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)



    }

    private fun displayNote() {
        // notes title and text
        val note = DataManager.notes[notePosition]
        includedLayoutBinding.textNoteTitle.setText(note.title)
        includedLayoutBinding.textNoteText.setText(note.text)

        // courses title and text
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        includedLayoutBinding.spinnerCourses.setSelection(coursePosition)
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
            // We're checking to see if the item.itemId corresponds with the menu item that
            // we created in the menu-main.xml which is menu layout resource
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu() // since this method calls onPrepareOptionsMenu(), we place it here so that it is called, each time next is tapped on.
    }

    // while we override onPrepareOptionsMenu() here
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition >= DataManager.notes.lastIndex){
            // getting reference to the menu item that we want to change
            val menuItem = menu?.findItem(R.id.action_next)
            if(menuItem != null){
                menuItem.icon = getDrawable(R.drawable.ic_baseline_block_24_red)
                menuItem.isEnabled = false
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

}
