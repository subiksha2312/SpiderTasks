package com.example.spidertask0

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.widget.TextClock
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.pow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


class SpiFactor : AppCompatActivity() {
    var hrs:Int=0                      //declaring member variables
    var mins:Int=0
    var sec:Int=0
    var spi:Double=0.00
    var factorial: Long=1
    lateinit var gameTimer: CountDownTimer
    var millisleft:Long = 90000       //setting the countdown timer showing time to 90 seconds after which ,activity itself will refresh and continue normally

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)  //setting orientation to only portrait
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spi_factor)

        val calendar = Calendar.getInstance() //getting calendar for hour,minute,second
        hrs = calendar.get(Calendar.HOUR)     //getting hour
        mins = calendar.get(Calendar.MINUTE)  //getting minute
        sec = calendar.get(Calendar.SECOND)   //getting second
        startTimer()

    }
    fun spifactorcalc(){      //to calculate the numerator which is a factorial
        factorial = 1

        if(mins==0 && sec==0){  //when seconds and mins are 0
            spi=0.00
        }

        else {
            for (i in 1..hrs) { //calculating factorial
                factorial = factorial * i
            }
        }
        Log.d("factorial",factorial.toString())
        var minssec=((pow(mins.toDouble(), 3.0))+sec)  //calculating the denominator
        Log.d("minssec",minssec.toString())
        spi=(factorial/minssec) //getting the spi value
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun currenttime(){           //function to get and display current time
        var timer=findViewById(R.id.timedisplay) as TextView
        val c = Calendar.getInstance()
        hrs = c.get(Calendar.HOUR)
        sec = c.get(Calendar.SECOND)
        mins = c.get(Calendar.MINUTE)

        timer.setText("$hrs:$mins:$sec") //setting the time
    }

    fun startTimer(){         //to start the timer
        gameTimer=object: CountDownTimer(millisleft,1000){
            override fun onTick(millisUntilFinished: Long) {
                millisleft = millisUntilFinished //for the countdown
                currenttime()           //function call
                ShowSpiFactor()         //function call
            }

            override fun onFinish() {
                CloseActivity()     //function call
                RestartActivity()   //function call

            }
        }.start()

    }

    override fun onDestroy() {  //function to destroy timer so multiple timers arent running when the activity is closed
        super.onDestroy()
        gameTimer.cancel()
    }

    fun RestartActivity() {
        val intent = Intent(this, SpiFactor::class.java)  //to restart the activity after 90 seconds
        startActivity(intent)
    }

    fun CloseActivity() {     //for activity to close and then open so that when back press is done it doesnt show previous time running screen
        this.finish()
    }


    fun ShowSpiFactor() {
        spifactorcalc()   //function call
        var spifactor=findViewById(R.id.spifactor) as TextView //to find the textview
        val spiprecision = String.format("%.10f", spi)         //passing spi value to another variable to display it
        spifactor.setText("The Spi factor is: $spiprecision")  //displaying value using settext
    }


}
