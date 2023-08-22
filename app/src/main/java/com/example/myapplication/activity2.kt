package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Activity2 : AppCompatActivity() {

    lateinit var editName: EditText
    lateinit var editemail: EditText
    lateinit var editno: EditText
    lateinit var editpass: EditText
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private lateinit var userdao: UserDao


    //database = Firebase.database.reference
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        // Initialize Firebase Auth
        auth = Firebase.auth
        editName = findViewById(R.id.name)
        editemail = findViewById(R.id.email)
        editno = findViewById(R.id.no)
        editpass = findViewById(R.id.Password)

        val buttonClick2 = findViewById<Button>(R.id.submit)
        buttonClick2.setOnClickListener {
            saveData()
        }
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()


        val buttonClick3 = findViewById<Button>(R.id.room_btn)
        buttonClick3.setOnClickListener {
            testDB()

        }
        userdao = db.userDao()

        val editName12 = findViewById<EditText>(R.id.name)
        val editemail12 = findViewById<EditText>(R.id.email)

        val btnsend1 = findViewById<Button>(R.id.passdata)

        btnsend1.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("Name", editName12.text.toString())
            bundle.putString("Email", editemail12.text.toString())

            val intent12 = Intent(this, datashow::class.java)
            intent12.putExtras(bundle)
            startActivity(intent12)
        }

    }

    private fun testDB()
    {
        lifecycleScope.launch(Dispatchers.IO)
        {
            userdao.insertdata(User(123,"abc","Rajkot"))
//            val books = userdao.insertdata()
//            for(book in books){
//                Log.i("MyTAG","id: ${book.id} name: ${book.name} author: ${book.city}")
//            }
        }
    }

    private fun saveData()
    {

        database = FirebaseDatabase.getInstance().getReference("User")
        val uname = editName.text.toString()
        val uemail= editemail.text.toString()
        val uno = editno.text.toString()
        val upass = editpass.text.toString()


        if(uname.isBlank() && uemail.isBlank() && uno.isBlank() && upass.isBlank())
        {
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show()
        }

        else
        {
            auth.createUserWithEmailAndPassword(uemail, upass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
                        database = FirebaseDatabase.getInstance().getReference("user")
                        val sid=database.push().key!!
                        val storedata=PassData(sid,uname,uemail,uno,upass)
                        database.child(uname).setValue(storedata).addOnSuccessListener {
                            Toast.makeText(this,"Successfully saved",Toast.LENGTH_LONG).show()
                            Handler(Looper.getMainLooper()).postDelayed({
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }, 1000)

                        }.addOnFailureListener{
                            Toast.makeText(this,"Failed to save",Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                    }
                }


        }
    }

}