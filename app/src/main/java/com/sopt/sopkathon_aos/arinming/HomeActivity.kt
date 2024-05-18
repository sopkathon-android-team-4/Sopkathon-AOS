package com.sopt.sopkathon_aos.arinming

import HomeViewModel
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.sopt.sopkathon_aos.R
import com.sopt.sopkathon_aos.data.request.RequestConcernsDto
import com.sopt.sopkathon_aos.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isButtonEnabled.observe(this) {
            binding.btnClear.isEnabled = it
            if (it) {
                binding.btnClear.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
                onClickClearButton(RequestConcernsDto(binding.etLetter.text.toString()))
            } else {
                binding.btnClear.setBackgroundColor(ContextCompat.getColor(this, R.color.mg_bcbcbc))
            }
        }
        setupEditText()
    }

    private fun setupEditText() {
        binding.etLetter.addTextChangedListener {
            it?.let {
                viewModel.onTextInput(it)
                val lines = it.split("\n")
                if (lines.size > 6) {
                    val limitedText = lines.take(6).joinToString("\n")
                    binding.etLetter.setText(limitedText)
                    binding.etLetter.setSelection(limitedText.length)
                } else {
                    val newText = lines.joinToString("\n") { line ->
                        if (line.length > 16) line.substring(0, 16) else line
                    }
                    if (newText != it.toString()) {
                        binding.etLetter.setText(newText)
                        binding.etLetter.setSelection(newText.length)
                    }
                }
            }
        }
    }

    private fun onClickClearButton(request: RequestConcernsDto) {
        binding.btnClear.setOnClickListener {
            viewModel.onPostConcerns(request)
        }
    }
}
