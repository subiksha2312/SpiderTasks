package com.example.spidertask0

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1=findViewById<Button>(R.id.button1) //finding the button to be clicked to start intent
        button1.setOnClickListener{
            val intent= Intent(this,Lorentzfactorcalci::class.java) //Creating an intent so that when the button is clicked,it moves onto another activity
            startActivity(intent)  //starting said intent
        }
        val button2=findViewById<Button>(R.id.button2)  //finding the button to be clicked to start intent
        button2.setOnClickListener{
            val intent= Intent(this,LFcalculator::class.java)  //Creating an intent so that when the button is clicked,it moves onto another activity
            startActivity(intent)  //starting said intent
        }
        val button3=findViewById<Button>(R.id.button3)  //finding the button to be clicked to start intent
        button3.setOnClickListener{
            val intent= Intent(this,SpiFactor::class.java)  //Creating an intent so that when the button is clicked,it moves onto another activity
            startActivity(intent)  //starting said intent
        }
    }
}

