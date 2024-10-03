package com.example.mainactivity

import android.R
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity2 : AppCompatActivity() {
    private var editTextName: EditText? = null
    private var buttonNext: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        editTextName = findViewById<EditText>(R.id.editTextName)
        buttonNext = findViewById<Button>(R.id.buttonNext)

        // Set the click listener for the Next button
        buttonNext.setOnClickListener(View.OnClickListener { // Get the user’s name from EditText
            val name = editTextName.getText().toString()

            // Create an Intent to launch NameActivity
            val intent = Intent(
                this@MainActivity,
                NameActivity::class.java
            )

            // Pass the name to the intent
            intent.putExtra("userName", name)

            // Start NameActivity and expect a result
            ActivityCompat.startActivityForResult(intent, 1)
        })
    }
}

protected fun onPause() {
    super.onPause()

    // Get the SharedPreferences instance
    val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

    // Get the editor to write data
    val editor = sharedPreferences.edit()

    // Save the name entered in EditText to SharedPreferences
    editor.putString("savedName", editTextName.getText().toString())

    // Apply the changes
    editor.apply()
}
protected fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Initialize UI elements
    editTextName = findViewById(R.id.editTextName)
    buttonNext = findViewById(R.id.buttonNext)

    // Load the name from SharedPreferences if it exists
    val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val savedName = sharedPreferences.getString("savedName", "")

    if (!savedName!!.isEmpty()) {
        // Set the saved name in the EditText
        editTextName.setText(savedName)
    }

    // Set the click listener for the Next button
    buttonNext.setOnClickListener(View.OnClickListener {
        val name: String = editTextName.getText().toString()
        val intent: Intent = Intent(
            this@MainActivity,
            NameActivity::class.java
        )
        intent.putExtra("userName", name)
        startActivityForResult(intent, 1)
    })
}
protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    // Check which request we're responding to
    if (requestCode == 1) { // 1 is the request code we used when starting NameActivity
        if (resultCode == 0) {
            // User wants to change their name, do nothing
        } else if (resultCode == 1) {
            // User is happy, close the app
            finish() // This closes the current activity (MainActivity2)
        }
    }
}


companion object {
        private const val NAME_ACTIVITY_REQUEST_CODE = 1 // Request code for NameActivity
    }
}
