package edu.arizona.cast.stevenwerden.bloodglucoselevelmonitor

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import edu.arizona.cast.stevenwerden.bloodglucoselevelmonitor.databinding.FragmentMonitorDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class MonitorDetailFragment : Fragment() {

    private var _binding: FragmentMonitorDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Binding is null. Check if view is visible"
        }
    private lateinit var glucose: Glucose

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glucose = Glucose(
            date = Date()

        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentMonitorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateButton.text = SimpleDateFormat("MMM dd, yyyy").format(Date())

        binding.clearButton.setOnClickListener { view: View ->
            resetEditText(binding.fastingInput)
            resetEditText(binding.breakfastInput)
            resetEditText(binding.lunchInput)
            resetEditText(binding.dinnerInput)
            binding.displayResults.text = "" /* TextView instead of EditText */
            binding.displayResults.setTextColor(Color.WHITE)
        }

        binding.calculateButton.setOnClickListener() { view: View ->
            if(checkText(binding.fastingInput) &&
            checkText(binding.breakfastInput) &&
            checkText(binding.lunchInput) &&
            checkText(binding.dinnerInput)) {

                binding.displayResults.text = Date().toString() + "\n" +
                "Fasting: " + checkLevel(binding.fastingInput, 99) + "\n" +
                "Breakfast: " + checkLevel(binding.breakfastInput, 140) + "\n" +
                "Lunch: " + checkLevel(binding.lunchInput, 140) + "\n" +
                "Dinner: " + checkLevel(binding.dinnerInput, 140) + "\n"
                if(binding.displayResults.text.contains("Abnormal")) {
                    binding.displayResults.append("IsNormal: False")
                    binding.displayResults.setTextColor(Color.RED)
                }
                else {
                    binding.displayResults.append("IsNormal: True")
                    /* In case the user asked for two different calculations without using CLEAR*/
                    binding.fastingInput.setTextColor(Color.WHITE)
                    binding.breakfastInput.setTextColor(Color.WHITE)
                    binding.lunchInput.setTextColor(Color.WHITE)
                    binding.dinnerInput.setTextColor(Color.WHITE)
                    binding.displayResults.setTextColor(Color.WHITE)
                }

            }
            else {
                Toast.makeText(activity, "Please use numbers only!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.historyButton.setOnClickListener() {
            //"The History button does nothing at this time."
        }
    }

    fun checkText(check: EditText): Boolean {
        if(check.text.isNotEmpty() && check.text.isDigitsOnly()) {
            return true
        }
        return false
    }

    fun checkLevel(glucoseLevel: EditText, upperLimit: Int): String {
        if(glucoseLevel.text.toString().toInt() < 70 || glucoseLevel.text.toString().toInt() > upperLimit) {
            glucoseLevel.setTextColor(Color.RED)
            return "Abnormal"
        }
        return "Normal"
    }

    fun resetEditText(reset: EditText) {
        reset.setText("")
        reset.setTextColor(Color.WHITE)
    }
}