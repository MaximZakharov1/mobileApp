package com.example.mobileapp

import NetworkManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WordActivity : AppCompatActivity() {
    private lateinit var radioButtonGroup: RadioGroup
    private lateinit var checkButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_word)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = findViewById<ImageView>(R.id.page_back)
        backButton.setOnClickListener {
            finish()
        }

        val networkManager = NetworkManager(this)
        networkManager.observe(this) { hasNetwork ->
            if (!hasNetwork) {
                val intent = Intent(this, ConnectionActivity::class.java)
                startActivity(intent)
            }
        }

        radioButtonGroup = findViewById(R.id.radioGroup)
        checkButton = findViewById(R.id.check)

        checkButton.setOnClickListener {
            val selectedRadioButtonId = radioButtonGroup.checkedRadioButtonId

            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val correctRadioButton = findViewById<RadioButton>(R.id.sadovnik)

                if (selectedRadioButton.tag == "correct") {
                    val correctRoundedCornersDrawable = ContextCompat.getDrawable(this, R.drawable.correct_word_button)
                    correctRadioButton.background = correctRoundedCornersDrawable
                } else {
                    val incorrectRoundedCornersDrawable = ContextCompat.getDrawable(this, R.drawable.wrong_word_button)
                    selectedRadioButton.background = incorrectRoundedCornersDrawable
                    val correctRoundedCornersDrawable = ContextCompat.getDrawable(this, R.drawable.correct_word_button)
                    correctRadioButton.background = correctRoundedCornersDrawable
                }

                for (i in 0 until radioButtonGroup.childCount) {
                    val radioButton: RadioButton = radioButtonGroup.getChildAt(i) as RadioButton
                    radioButton.isEnabled = false
                }
                checkButton.text = getString(R.string.next)
                checkButton.setOnClickListener {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        }


    }
}