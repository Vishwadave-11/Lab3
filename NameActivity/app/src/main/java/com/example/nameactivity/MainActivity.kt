package com.example.nameactivity

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    class NameActivity : AppCompatActivity() {
        private var textViewWelcome: TextView? = null
        private var buttonThankYou: Button? = null
        private var buttonNoThanks: Button? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_name)

            // Initialize UI elements
            textViewWelcome = findViewById<TextView>(R.id.textViewWelcome)
            buttonThankYou = findViewById<Button>(R.id.buttonThankYou)
            buttonNoThanks = findViewById<Button>(R.id.buttonNoThanks)

            // Get the name passed from MainActivity
            val intent = intent
            val userName = intent.getStringExtra("userName")

            // Display the name in the TextView
            textViewWelcome.setText("Welcome $userName!")

            // Set up button click listeners
            buttonThankYou.setOnClickListener(View.OnClickListener { v: View? ->
                setResult(1) // User is happy with the name
                finish() // Return to MainActivity
            })

            buttonNoThanks.setOnClickListener(View.OnClickListener { v: View? ->
                setResult(0) // User doesn't like the name
                finish() // Return to MainActivity
            })
        }
    }
