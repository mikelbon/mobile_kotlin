package com.mcarhuas.firstproject

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var textView: TextView

    companion object {
        private const val PREFS_NAME = "MyPrefsFile"
        private const val TEXT_KEY = "savedText"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editText = findViewById(R.id.edit_text_id)
        button = findViewById(R.id.button_id)
        textView = findViewById(R.id.text_view_id)
        button.setOnClickListener {
            val inputText = editText.text.toString()
            textView.text = inputText
        }
        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedText = settings.getString(TEXT_KEY, "")
        textView.text = savedText

        button.setOnLongClickListener {
            val inputText = editText.text.toString()
            textView.text = inputText
            val editor = settings.edit()
            editor.putString(TEXT_KEY, inputText)
            editor.apply()
            true
        }
    }
}