package com.example.mobileapp

import NetworkManager
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class OnboardingActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_onboarding1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.skip_onboarding)

        // Создание строки с текстом
        val text = "Skip onboarding"

        // Создание SpannableString
        val spannableString = SpannableString(text)

        // Настройка кликабельной области для слова "Skip onboarding"
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Действие при нажатии
                val intent = Intent(this@OnboardingActivity1, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        // Установка clickableSpan для всей строки
        spannableString.setSpan(
            clickableSpan,
            0,
            text.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Установка текста и поддержка кликабельных ссылок
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()



        val nextButton = findViewById<Button>(R.id.next)
        nextButton.setOnClickListener {
            val intent = Intent(this, OnboardingActivity2::class.java)
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