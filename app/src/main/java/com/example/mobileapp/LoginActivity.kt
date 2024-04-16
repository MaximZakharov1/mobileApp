package com.example.mobileapp

import NetworkManager
import android.content.Intent
import android.net.Uri
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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textView = findViewById<TextView>(R.id.googlefor)

        // Создание строки с текстом
        val text = "Use can use <a href=\"https://www.google.com\">Google for</a> sign in."

        // Создание SpannableString
        val spannableString = SpannableString.valueOf(android.text.Html.fromHtml(text))

        // Настройка кликабельной области для слова "Google for"
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Действие при нажатии
                val url = "https://www.google.com"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }

        // Установка clickableSpan для слова "Google for"
        spannableString.setSpan(
            clickableSpan,
            spannableString.indexOf("Google for"),
            spannableString.indexOf("Google for") + "Google for".length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Установка текста и поддержка кликабельных ссылок
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()

        val passwordInputLayout: TextInputLayout = findViewById(R.id.PasswordLayout)
        val passwordInput: TextInputEditText = findViewById(R.id.PasswordInput)

        val textView1 = findViewById<TextView>(R.id.signup)

        // Создание строки с текстом
        val text1 = "Not your member? <a href=\"signup://\">Signup</a>."

        // Создание SpannableString
        val spannableString1 = SpannableString.valueOf(android.text.Html.fromHtml(text1))

        // Настройка кликабельной области для слова "Signup"
        val clickableSpan1 = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Действие при нажатии
                val intent = Intent(this@LoginActivity, SignupActivity::class.java)
                startActivity(intent)
            }
        }

        // Установка clickableSpan для слова "Signup"
        spannableString1.setSpan(
            clickableSpan1,
            spannableString1.indexOf("Signup"),
            spannableString1.indexOf("Signup") + "Signup".length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Установка текста и поддержка кликабельных ссылок
        textView1.text = spannableString
        textView1.movementMethod = LinkMovementMethod.getInstance()

        val backButton = findViewById<ImageView>(R.id.page_back)
        backButton.setOnClickListener {
            finish()
        }

        passwordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Скрыть подсказку hint, если вводится текст
                passwordInputLayout.hint = ""
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val networkManager = NetworkManager(this)
        networkManager.observe(this) { hasNetwork ->
            if (!hasNetwork) {
                val intent = Intent(this, ConnectionActivity::class.java)
                startActivity(intent)
            }
        }

        val againButton = findViewById<Button>(R.id.loginButton)
        againButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}