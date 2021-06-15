package com.example.spidertask0

import android.content.Context
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
    var mVelocity:Double = 0.0
    val mSpeedLight:Double =3* Math.pow(10.0, 8.0)
    var mVelocityRatio:Double = 0.0
    var mGamma :Double=0.00000000
    var userGamma :Double=0.00000000
    var attemptcounter:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lfcalculator)

        var button=findViewById(R.id.button) as Button
        button.setOnClickListener(){
            checkbuttonclicked()
        }
    }

    fun checkbuttonclicked(){
        var etvelocity=findViewById(R.id.uservelocity) as EditText
        var strtext=(etvelocity.text).toString()
        if(strtext.length>0){
        mVelocity = (etvelocity.text).toString().toDouble()
            if (mVelocity<mSpeedLight){
        var etlf=findViewById(R.id.userlf) as EditText
        userGamma=(etlf.text).toString().toDouble()

        mVelocityRatio =pow(mVelocity, 2.0) / pow(mSpeedLight, 2.0)
        mGamma = 1/ sqrt(1 - mVelocityRatio)
        mGamma = (String.format("%.8f", mGamma)).toDouble()
        userGamma=(String.format("%.8f",userGamma)).toDouble()

        Log.d("Gamma", mGamma.toString())
        Log.d("usergamma", userGamma.toString())


        if(userGamma==mGamma){
            getWindow().getDecorView().setBackgroundColor(Color.DKGRAY)
        }
        else {
            attemptcounter++
            if (attemptcounter > 3) {
                var rightanswer = findViewById(R.id.rightanswer) as TextView
                getWindow().getDecorView().setBackgroundColor(Color.BLACK)
                var button = findViewById(R.id.button) as Button
                button.visibility = View.GONE
                rightanswer.setText("The correct answer is: $mGamma")
            } else {
                getWindow().getDecorView().setBackgroundColor(Color.LTGRAY)
                val v = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    v.vibrate(500)
                }
            }
        }} else{
                Toast.makeText(applicationContext, "Please enter a velocity lesser than the speed of light", Toast.LENGTH_LONG).show()
            }
        }
    }
}

