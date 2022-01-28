package com.jeffersonbueno.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeffersonbueno.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.cos

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0){
            binding.tipResult.text = ""
            return
        }

        val selectedId = binding.tipOptions.checkedRadioButtonId

        val tipPercentage: Double = when(selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_ten_percent -> 0.10
            else -> 0.0
        }

        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}