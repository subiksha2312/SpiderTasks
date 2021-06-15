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

        val button1=findViewById<Button>(R.id.button1)
        button1.setOnClickListener{
            val intent= Intent(this,Lorentzfactorcalci::class.java)
            startActivity(intent)
        }
        val button2=findViewById<Button>(R.id.button2)
        button2.setOnClickListener{
            val intent= Intent(this,LFcalculator::class.java)
            startActivity(intent)
        }
        val button3=findViewById<Button>(R.id.button3)
        button3.setOnClickListener{
            val intent= Intent(this,SpiFactor::class.java)
            startActivity(intent)
        }
    }
}

