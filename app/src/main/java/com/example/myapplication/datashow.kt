package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class datashow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datashow)
        val id = findViewById<TextView>(R.id.Id12)
        val name = findViewById<TextView>(R.id.Name12)

        val bundle = intent.extras
        if (bundle != null){
            id.text = "Name : ${bundle.getString("Name")}"
            name.text = "E-mail : ${bundle.getString("Email")}"
        }
    }
}