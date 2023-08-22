package com.example.myapplication

import android.os.Bundle
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R.id.a1
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fregment

class buttons : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)
        auth = Firebase.auth

        // when button is clicked, show the alert
        val btnShowAlert=findViewById<TextView>(R.id.a1)
        btnShowAlert.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Are you sure you want to logout ?")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Log out", DialogInterface.OnClickListener {

                        dialog, id -> finish()
                        auth.signOut()
                    startActivity(Intent(this,MainActivity::class.java))


                })
                // negative button text and action
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Log out")
            // show alert dialog
            alert.show()

            }
        var snack1 = findViewById<Button>(R.id.snack)
        snack1.setOnClickListener {
            val snack = Snackbar.make(it,"Yo what's up",Snackbar.LENGTH_LONG)
            snack.show()
        }
        val buttonClick = findViewById<Button>(R.id.list_button)
        buttonClick.setOnClickListener {
            val intent = Intent(this, listview::class.java)
            startActivity(intent)
        }
        val buttonClick1 = findViewById<Button>(R.id.recycle_btn)
        buttonClick1.setOnClickListener {
            val intent1 = Intent(this, recycle_view::class.java)
            startActivity(intent1)
        }
        val buttonClick2 = findViewById<Button>(R.id.f1)
        buttonClick2.setOnClickListener {
            val intent12 = Intent(this, fregment::class.java)
            startActivity(intent12)
        }
        val buttonClick3 = findViewById<Button>(R.id.wb_btn)
        buttonClick3.setOnClickListener {
            val intent123 = Intent(this, webview::class.java)
            startActivity(intent123)
        }
    }
}
