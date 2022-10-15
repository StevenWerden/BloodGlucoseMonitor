package edu.arizona.cast.stevenwerden.bmiheartratecalculator

import android.view.View
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class HeartRateViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    val ageSave = savedStateHandle.getLiveData<Int>("age", 0)

    fun heartRateFormula(age: TextView, HRresults: TextView) :Double {
        if (age.text.isEmpty() && ageSave.value!! > 0) {
            displayHeartResults(ageSave.value!!, HRresults)
        }
        else if(checkText(age)) {
            var ageCheck = age.text.toString().toInt()
            savedStateHandle["ageSave"] = ageCheck
            displayHeartResults(ageCheck, HRresults)
        }
        else {
            return -1.0 /* Returns negative to call Toast error message */
        }
        return 0.0 /* Nothing went wrong */
    }

    fun displayHeartResults(ageCheck: Int, HRresults: TextView) {
        var maximum = 220 - ageCheck
        var lowerRange = maximum * 0.5
        var upperRange = maximum * 0.85
        val df: DecimalFormat = DecimalFormat("#.0")
        HRresults.text = "Maximum: " + maximum + "\n Target: " +
                df.format(lowerRange) + " - " + df.format(upperRange)
        HRresults.visibility = View.VISIBLE
    }

    fun checkText(test: TextView): Boolean {
        if (test.text.toString().length > 0) {
            if (test.text.toString().isDigitsOnly()) {
                if (test.text.toString().toInt() >= 0) {
                    return true
                }
            }
        }
        return false
    }

    fun changeAge(age: Int){
        savedStateHandle["ageSave"] = age
    }

    fun heartRateClear(ageInput: TextView, HRresults: TextView) {
        ageInput.text = ""
        HRresults.visibility = View.INVISIBLE
    }
}