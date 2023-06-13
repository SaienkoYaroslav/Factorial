package com.example.factorial

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.factorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModelObserve()
        binding.buttonCalculate.setOnClickListener {
            viewModel.calculate(binding.editTextUserInput.text.toString())
        }
    }

    private fun viewModelObserve() {

        viewModel.state.observe(this) {
            if (it.isError) {
                Toast.makeText(this, "The field is empty", Toast.LENGTH_SHORT).show()
            }
            if (it.isProgress) {
                binding.progressBarCalculating.visibility = View.VISIBLE
                binding.buttonCalculate.isEnabled = false
            } else {
                binding.progressBarCalculating.visibility = View.GONE
                binding.buttonCalculate.isEnabled = true
            }
            binding.textViewResult.text = it.factorial

        }

    }

}