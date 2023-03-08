package com.example.notesapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val txtFirstName = findViewById<EditText>(R.id.txtFirstName)
        val txtLastName = findViewById<EditText>(R.id.txtLastName)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnSubmit = findViewById<Button>(R.id.btnCreateUser)



        btnSubmit.setOnClickListener() {

            val pref = getSharedPreferences("USER_DETAILS", Context.MODE_PRIVATE)
            val editor = pref.edit()

            editor.putString("FIRST_NAME", txtFirstName.text.toString())
            editor.putString("LAST_NAME", txtLastName.text.toString())
            editor.putString("EMAIL", txtEmail.text.toString())
            editor.putInt("PASSWORD", txtPassword.text.toString().hashCode())
            editor.putBoolean("USER_CREATED", true)

            editor.apply()

            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}