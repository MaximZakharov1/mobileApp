package com.example.mobileapp

import NetworkManager
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
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

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.login)

        // Создание строки с текстом
        val text = "Already you member? <a href=\"login://\">Login</a>."

        // Создание SpannableString
        val spannableString = SpannableString.valueOf(android.text.Html.fromHtml(text))

        // Настройка кликабельной области для слова "Login"
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Действие при нажатии
                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        // Установка clickableSpan для слова "Login"
        spannableString.setSpan(
            clickableSpan,
            spannableString.indexOf("Login"),
            spannableString.indexOf("Login") + "Login".length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Установка текста и поддержка кликабельных ссылок
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()

        val backButton = findViewById<ImageView>(R.id.page_back)
        backButton.setOnClickListener {
            finish()
        }

        val continueButton = findViewById<Button>(R.id.contin)
        continueButton.setOnClickListener {
            val intent = Intent(this, Signup2Activity::class.java)
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