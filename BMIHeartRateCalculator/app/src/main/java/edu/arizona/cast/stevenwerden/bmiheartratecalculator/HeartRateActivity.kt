package edu.arizona.cast.stevenwerden.bmiheartratecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import androidx.activity.viewModels


class HeartRateActivity : AppCompatActivity() {

    private val heartRateViewClass: HeartRateViewModel by viewModels()
    private lateinit var ageInput: TextInputEditText
    private lateinit var HRcalculateButton: Button
    private lateinit var HRclearButton: Button
    private lateinit var HRresults: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_rate)

        /* Grab Identifiers */
        ageInput = findViewById(R.id.ageInput)
        HRcalculateButton = findViewById(R.id.HRCalculateButton)
        HRclearButton = findViewById(R.id.HRClearButton)
        HRresults = findViewById(R.id.heartRateReults)

        HRcalculateButton.setOnClickListener{ view: View ->
            var returnHeart = heartRateViewClass.heartRateFormula(ageInput, HRresults)
            if(returnHeart  < 0) {
                Toast.makeText(this, "Please use positive values", Toast.LENGTH_SHORT).show()
            }

        }

        HRclearButton.setOnClickListener{ view: View ->
            heartRateViewClass.heartRateClear(ageInput, HRresults)
        }
    }
}