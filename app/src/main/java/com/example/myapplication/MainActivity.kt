package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
lateinit var mail1: EditText
lateinit var pass1: EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        mail1 = findViewById(R.id.mail1)
        pass1 = findViewById(R.id.pass1)

        val btnclick = findViewById<Button>(R.id.login1)
        btnclick.setOnClickListener {
            checkdata();
        }

        val buttonClick = findViewById<Button>(R.id.register)
        buttonClick.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }
        val buttonClick1 = findViewById<Button>(R.id.gotobuttons)
        buttonClick1.setOnClickListener {
            val intent1 = Intent(this, buttons::class.java)
            startActivity(intent1)
        }
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, buttons::class.java)
            startActivity(intent)
        }
        else
        {
            btnclick.setOnClickListener {
                checkdata();
            }
        }
    }

    private fun checkdata() {
        val umail = mail1.text.toString()
        val upass= pass1.text.toString()
        auth.signInWithEmailAndPassword(umail, upass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Sucessful", Toast.LENGTH_LONG).show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, buttons::class.java)
                        startActivity(intent)
                        finish()
                    }, 1000)
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            }
    }
}
