package com.example.mobileapp

import NetworkManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val continueButton = findViewById<CardView>(R.id.myProfile)
        continueButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val continueButton1 = findViewById<CardView>(R.id.animal)
        continueButton1.setOnClickListener {
            val intent = Intent(this, AnimalsActivity::class.java)
            startActivity(intent)
        }

        val continueButton2 = findViewById<CardView>(R.id.word)
        continueButton2.setOnClickListener {
            val intent = Intent(this, WordActivity::class.java)
            startActivity(intent)
        }

        val continueButton3 = findViewById<CardView>(R.id.listening)
        continueButton2.setOnClickListener {
            val intent = Intent(this, ListeningActivity::class.java)
            startActivity(intent)
        }

        val networkManager = NetworkManager(this)
        networkManager.observe(this) { hasNetwork ->
            if (!hasNetwork) {
                val intent = Intent(this, ConnectionActivity::class.java)
                startActivity(intent)
            }
        }
    }
}