package com.example.spidertask0

import android.content.Intent
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
    var hrs:Int=0
    var mins:Int=0
    var sec:Int=0
    var spi:Double=0.00
    var factorial: Long=1
    lateinit var gameTimer: CountDownTimer
    var millisleft:Long = 90000

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spi_factor)


        // var ttobedisplayed=findViewById(R.id.timedisplay) as TextView
        // val currentDateTime=LocalDateTime.now()
        //ttobedisplayed.setText(currentDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)))

        val calendar = Calendar.getInstance()
        hrs = calendar.get(Calendar.HOUR)
        mins = calendar.get(Calendar.MINUTE)
        sec = calendar.get(Calendar.SECOND)
        startTimer()
    /*
        currenttime()
        spifactorcalc()
        var spifactor=findViewById(R.id.spifactor) as TextView
        spifactor.setText("The Spi factor is: $spi")

         */

    }
    fun spifactorcalc(){
        factorial = 1

        if(mins==0 && sec==0){
            spi=0.00
        }

        else {
            for (i in 1..hrs) {
                factorial = factorial * i
            }
        }
        Log.d("factorial",factorial.toString())
        var minssec=((pow(mins.toDouble(), 3.0))+sec)
        Log.d("minssec",minssec.toString())
        spi=(factorial/minssec)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun currenttime(){
        var timer=findViewById(R.id.timedisplay) as TextView
        val c = Calendar.getInstance()
        hrs = c.get(Calendar.HOUR)
        sec = c.get(Calendar.SECOND)
        mins = c.get(Calendar.MINUTE)

        timer.setText("$hrs:$mins:$sec")
    }

    fun startTimer(){
        gameTimer=object: CountDownTimer(millisleft,1000){
            override fun onTick(millisUntilFinished: Long) {
                millisleft = millisUntilFinished
                currenttime()
                ShowSpiFactor()
            }

            override fun onFinish() {
                CloseActivity()
                RestartActivity()

            }
        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        gameTimer.cancel()
    }

    fun RestartActivity() {
        val intent = Intent(this, SpiFactor::class.java)
        startActivity(intent)
    }

    fun CloseActivity() {
        this.finish()
    }


    fun ShowSpiFactor() {
        spifactorcalc()
        var spifactor=findViewById(R.id.spifactor) as TextView
        val spiprecision = String.format("%.10f", spi)
        spifactor.setText("The Spi factor is: $spiprecision")
    }


}
