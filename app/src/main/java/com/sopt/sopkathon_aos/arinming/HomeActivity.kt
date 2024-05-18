package com.sopt.sopkathon_aos.arinming

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.sopt.sopkathon_aos.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEditText()
    }

    private fun setupEditText() {
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val lines = it.split("\n")
                    if (lines.size > 6) {
                        val limitedText = lines.take(6).joinToString("\n")
                        binding.editText.setText(limitedText)
                        binding.editText.setSelection(limitedText.length)
                    } else {
                        val newText = lines.joinToString("\n") { line ->
                            if (line.length > 16) line.substring(0, 16) else line
                        }
                        if (newText != it.toString()) {
                            binding.editText.setText(newText)
                            binding.editText.setSelection(newText.length)
                        }
                    }
                }
            }
        })
    }
}
