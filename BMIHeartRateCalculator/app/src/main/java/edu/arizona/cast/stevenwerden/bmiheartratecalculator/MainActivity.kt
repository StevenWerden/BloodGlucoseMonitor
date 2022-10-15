package edu.arizona.cast.stevenwerden.bmiheartratecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val bmiClass: bmiViewModel by viewModels()
    private lateinit var calculateButton: Button
    private lateinit var clearButton: Button
    private lateinit var feetInput: TextInputEditText
    private lateinit var inchesInput: TextInputEditText
    private lateinit var weightInput: TextInputEditText
    private lateinit var bmiResults: TextView
    private lateinit var heartRateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Grab identifiers */
        feetInput = findViewById(R.id.feetInput)
        inchesInput = findViewById(R.id.inchesInput)
        weightInput = findViewById(R.id.weightInput)
        calculateButton = findViewById(R.id.calculateButton)
        clearButton = findViewById(R.id.clearButton)
        bmiResults = findViewById(R.id.bmiResults)
        heartRateButton = findViewById(R.id.heartRateButton)

        calculateButton.setOnClickListener{ view: View ->
            var BMI = bmiClass.bmiFormula(feetInput, inchesInput, weightInput)
            if (BMI < 0){
                Toast.makeText(this, "Please use positive values", Toast.LENGTH_SHORT).show()
            }
            else {
                bmiClass.displayBMI(BMI, bmiResults)
            }
        }

        clearButton.setOnClickListener{ view: View ->
            bmiClass.clearAll(feetInput, inchesInput, weightInput, bmiResults)
        }

        heartRateButton.setOnClickListener { view: View ->
            val HRintent = Intent(this, HeartRateActivity::class.java)
            startActivity(HRintent)
        }
    }
}