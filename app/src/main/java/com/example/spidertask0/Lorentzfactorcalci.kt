package com.example.spidertask0



import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.pow
import java.lang.Math.sqrt


class Lorentzfactorcalci : AppCompatActivity() {
    var mVelocity:Double = 0.0
    val mSpeedLight:Double =3*pow(10.0,8.0)
    var mVelocityRatio:Double = 0.0
    var mGamma:Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lorentzfactorcalci)

        var button=findViewById(R.id.Check) as Button
        button.setOnClickListener(){
                checkbuttonclicked()
        }

    }

    fun checkbuttonclicked(){
        var etvelocity=findViewById(R.id.valueV) as EditText
        var strtext=(etvelocity.text).toString()
        if(strtext.length>0) {
            mVelocity = (etvelocity.text).toString().toDouble()
            if (mVelocity<mSpeedLight){
                mVelocityRatio = pow(mVelocity,2.0)/pow(mSpeedLight,2.0)
                mGamma = 1/sqrt(1-mVelocityRatio)
                val sGamma = String.format("%.10f", mGamma)
                var answer=findViewById(R.id.textView7) as TextView
                answer.setText("The Lorentz factor is: $sGamma")

            }
            else {
                Toast.makeText(applicationContext, "Please enter a velocity lesser than the speed of light", Toast.LENGTH_LONG).show()
            }
        }



    }
}