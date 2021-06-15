package com.example.spidertask0

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Math.sqrt
import java.lang.Math.pow
import java.math.BigDecimal


class LFcalculator : AppCompatActivity() {
    var mVelocity:Double = 0.0                   //declaring member variables
    val mSpeedLight:Double =3* Math.pow(10.0, 8.0)
    var mVelocityRatio:Double = 0.0
    var mGamma :Double=0.00000000
    var userGamma :Double=0.00000000
    var attemptcounter:Int = 0                //counter to check if tries are more than 3

    override fun onCreate(savedInstanceState: Bundle?) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) //orientation always will be portrait
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lfcalculator)

        var button=findViewById(R.id.button) as Button                //finding button
        button.setOnClickListener(){
            checkbuttonclicked()                                      //calling function
        }
    }

    fun checkbuttonclicked() {
        var etvelocity =
            findViewById(R.id.uservelocity) as EditText         //Same thing that was done in initially calculating LF has been done here
        var strtext = (etvelocity.text).toString()

        var etlf = findViewById(R.id.userlf) as EditText
        var strtext1 = (etlf.text).toString()
        Toast.makeText(
            applicationContext,
            "Please enter the values",
            Toast.LENGTH_LONG
        ).show()
        if (strtext.length > 0) {
            if (strtext1.length > 0) {
                mVelocity = (etvelocity.text).toString().toDouble()
                if (mVelocity < mSpeedLight) {
                    var etlf = findViewById(R.id.userlf) as EditText
                    userGamma = (etlf.text).toString().toDouble()

                    mVelocityRatio = pow(mVelocity, 2.0) / pow(mSpeedLight, 2.0)
                    mGamma = 1 / sqrt(1 - mVelocityRatio)
                    mGamma = (String.format("%.8f", mGamma)).toDouble()
                    userGamma = (String.format(
                        "%.8f",
                        userGamma
                    )).toDouble()         //To get what user's value of gamma is

                    Log.d("Gamma", mGamma.toString())
                    Log.d("usergamma", userGamma.toString())


                    if (userGamma == mGamma) {                                       //if user's value of gamma and actual value of gamma is same ,enters loop
                        getWindow().getDecorView()
                            .setBackgroundColor(Color.DKGRAY) //sets background color to dark gray
                    } else {
                        attemptcounter++                                 //incrementing counter to check number of attempts
                        if (attemptcounter > 3) {
                            var rightanswer = findViewById(R.id.rightanswer) as TextView
                            getWindow().getDecorView()
                                .setBackgroundColor(Color.BLACK) //if more than 3 tries sets bg color black
                            var button =
                                findViewById(R.id.button) as Button           //finding the button
                            button.visibility =
                                View.GONE                              //removing visibility of button so user cannot attempt more again
                            rightanswer.setText("The correct answer is: $mGamma")
                        } else {
                            getWindow().getDecorView()
                                .setBackgroundColor(Color.LTGRAY) //if three tries are not up yet, set bg color to grey
                            val v =
                                (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator) //vibration everytime wrong answer is given
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                v.vibrate(
                                    VibrationEffect.createOneShot(
                                        500,                 //time of vibration
                                        VibrationEffect.DEFAULT_AMPLITUDE
                                    )
                                )
                            } else {
                                v.vibrate(500)
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Please enter a velocity lesser than the speed of light",
                        Toast.LENGTH_LONG
                    ).show() //if the user inputs a peed greater than light
                }
            }
        }
    }
}

