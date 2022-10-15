package edu.arizona.cast.stevenwerden.bmiheartratecalculator

import android.graphics.Color
import java.text.DecimalFormat
import android.view.View
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel

class bmiViewModel: ViewModel(){

    /* This checks if the user input is valid. */
    fun bmiFormula(feet: TextView, inches: TextView, weight: TextView) :Double {
        var feetCheck = 0.0
        var inchesCheck = 0.0
        var weightCheck = 0.0
        if(checkTextView(feet) && checkTextView(inches) && checkTextView(weight)){
            feetCheck = feet.text.toString().toDouble() * 12
            inchesCheck = inches.text.toString().toDouble()
            weightCheck = weight.text.toString().toDouble()
        }
        else {
            return -1.0
        }
        var heightCheck = feetCheck + inchesCheck
        var BMI = (weightCheck * 703) / (heightCheck * heightCheck)
        return BMI
    }

    /* Helper function to generically check if a TextView is valid for the program. */
    fun checkTextView(test: TextView): Boolean {
        if (test.text.toString().length > 0) {
            if (test.text.toString().isDigitsOnly()) {
                if (test.text.toString().toDouble() >= 0) {
                    return true
                }
            }
        }
        return false
    }

    /* This will display the appropriate BMI result from the user's input. */
    fun displayBMI(bmi: Double, bmiResults: TextView) {
        var status = ""
        if(bmi < 18.5){
            bmiResults.setTextColor(Color.parseColor("#FFFFEB3B"))
            status ="Underweight"
        } else if(bmi >= 18.5 && bmi <= 24.9) {
            bmiResults.setTextColor(Color.parseColor("#FF48F44C"))
            status ="Normal"
        } else if(bmi > 24.9 && bmi < 30) {
            bmiResults.setTextColor(Color.parseColor("#FFFFEB3B"))
            status ="Overweight"
        } else {
            bmiResults.setTextColor(Color.parseColor("#FFF44336"))
            status ="Obese"
        }
        val df: DecimalFormat = DecimalFormat("#.0")
        bmiResults.text = status + " - BMI: " + df.format(bmi)
        bmiResults.visibility = View.VISIBLE
    }

    /* This clears the text input and display boxes. */
    fun clearAll(feetInput: TextView, inchesInput: TextView, weightInput: TextView, bmiResults: TextView){
        feetInput.text = ""
        inchesInput.text = ""
        weightInput.text = ""
        bmiResults.visibility = View.INVISIBLE
    }

    /* */
}