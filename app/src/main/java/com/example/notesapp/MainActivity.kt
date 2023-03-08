package com.example.notesapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check if user has been created and navigate user as needed

        val pref = getSharedPreferences( "USER_DETAILS", Context.MODE_PRIVATE)

        val userCreated = pref.getBoolean("USER_CREATED", false)

        if( !userCreated ) {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
        }

        val userName = pref.getString("FIRST_NAME", "")

        val btnCreateNote = findViewById<Button>(R.id.btnCreate)
        val lblGreeting = findViewById<TextView>(R.id.lblGreeting)
        val noteRecycler = findViewById<RecyclerView>(R.id.notesRecycler)

        lblGreeting.text = "Welcome, ${userName}"
        // Pull in notes from NotesDatabase
        val notesDB = Room.databaseBuilder(this, NoteDatabase::class.java, "note").allowMainThreadQueries().build()
        val noteDao = notesDB.noteDao()
        val notesList = noteDao.getAll()

        val manager = NoteListAdapter(notesList)
        noteRecycler.layoutManager = LinearLayoutManager(this)
        noteRecycler.adapter = manager
        //lblNotes.setText(notes)
        btnCreateNote.setOnClickListener() {
            // Navigate to CreateNotesView
            startActivity(Intent(this, CreateNotesActivity::class.java))
        }
    }

}