package com.example.spidertask0



import android.content.pm.ActivityInfo
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
    var mVelocity:Double = 0.0                //declaring member variables of the class
    val mSpeedLight:Double =3*pow(10.0,8.0)
    var mVelocityRatio:Double = 0.0
    var mGamma:Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) //orientation will always be portrait
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lorentzfactorcalci)

        var button=findViewById(R.id.Check) as Button //finding button to be clicked ,calling what needs to be done when its clicked
        button.setOnClickListener(){
                checkbuttonclicked()    //calling the function
        }

    }

    fun checkbuttonclicked(){
        var etvelocity=findViewById(R.id.valueV) as EditText   //finding the edittext space
        var strtext=(etvelocity.text).toString()               //converting this to string ,otherwise when button is clicked app crashes because some garbage value was stores in there
        if(strtext.length>0) {                                //enters only when length greater than 0
            mVelocity = (etvelocity.text).toString().toDouble()
            if (mVelocity<mSpeedLight){                                   //lorentz factor does not make sense if speed is bigger than light so stating that condition
                mVelocityRatio = pow(mVelocity,2.0)/pow(mSpeedLight,2.0) //calculating the v^2/c^2
                mGamma = 1/sqrt(1-mVelocityRatio)                        //determining the  lorentz factor
                val sGamma = String.format("%.10f", mGamma)             //giving 10 decimal precision
                var answer=findViewById(R.id.textView7) as TextView     //finding the text view to display the
                answer.setText("The Lorentz factor is: $sGamma")       //setting the text in the textview

            }
            else {
                Toast.makeText(applicationContext, "Please enter a velocity lesser than the speed of light", Toast.LENGTH_LONG).show()  //else condition if user input is greater than speed of light
            }
        }



    }
}