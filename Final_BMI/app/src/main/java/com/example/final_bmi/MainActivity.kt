package com.example.final_bmi

import android.os.Bundle
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var bmiResult: TextView
    private lateinit var healthStats: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        bmiResult = findViewById(R.id.bmi_result)
        healthStats = findViewById(R.id.health_status)

        val heightPicker = findViewById<NumberPicker>(R.id.height)
        val weightPicker = findViewById<NumberPicker>(R.id.weight)

        // Configure height picker
        heightPicker.minValue = 80
        heightPicker.maxValue = 250
        heightPicker.value = 150

        // Configure weight picker
        weightPicker.minValue = 30
        weightPicker.maxValue = 200
        weightPicker.value = 70

        // Set listeners to calculate BMI when values change
        heightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI(heightPicker.value, weightPicker.value)
        }

        weightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI(heightPicker.value, weightPicker.value)
        }
    }

    private fun calculateBMI(height: Int, weight: Int) {
        // Calculate BMI using the formula: weight / (height in meters)^2
        val heightInMeters = height / 100f
        val bmi = weight / (heightInMeters * heightInMeters)

        // Display BMI
        bmiResult.text = "BMI: ${String.format("%.2f", bmi)}"

        // Display health status based on BMI
        healthStats.text = when {
            bmi < 16 -> "Severely Underweight"
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal (Healthy)"
            bmi < 30 -> "Overweight"
            bmi < 35 -> "Obesity Class I (Moderate)"
            bmi < 40 -> "Obesity Class II (Severe)"
            else -> "Obesity Class III (Very Severe or Morbidly Obese)"
        }
    }
}
