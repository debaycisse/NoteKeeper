package com.gads2021_pluralsight.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.gads2021_pluralsight.notekeeper.databinding.ActivityNoteListBinding
import com.gads2021_pluralsight.notekeeper.databinding.ContentNoteListBinding

class NoteListActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNoteListBinding
    private lateinit var includedLayoutBinding: ContentNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        includedLayoutBinding = ContentNoteListBinding.inflate(layoutInflater)

//        val navController = findNavController(R.id.nav_host_fragment_content_note_list)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            val activityIntent = Intent(this,
                // the second parameter is the type information for our MainActivity class
                MainActivity::class.java)
            // to run the above created intent, we run it as follows.
            startActivity(activityIntent)

            // With the code in this fab.setOnClickListener, when the fab button is clicked
            // the MainActivity starts running.
        }

        includedLayoutBinding.listNotes.adapter = ArrayAdapter(this,
            // the next parameter is the layout to format the list, we're using one of the built-in
            android.R.layout.simple_list_item_1,
            // data to populate is the next parameter
            DataManager.notes)

        // Codes to allow users to select a note from the list and we get the info on which note is selected
        // we need to call the listnotes list
        includedLayoutBinding.listNotes.setOnItemClickListener {parent, view, postion, id ->
            val activityIntent = Intent(this, MainActivity::class.java )
            activityIntent.putExtra(EXTRA_NOTE_POSITION, postion) // position handles the position of the selected item. EXTRA_NOTE_POSITION is declared in Constants.kt
            startActivity(activityIntent)
        }

    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_note_list)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}