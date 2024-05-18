package com.sopt.sopkathon_aos.arinming

import HomeViewModel
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
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

        setupEditText()
        checkEditText()
        onClickClearButton()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
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

    private fun checkEditText() {
        viewModel.isButtonEnabled.observe(this) {
            binding.btnClear.isEnabled = it
            if (it) {
                binding.btnClear.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            } else {
                binding.btnClear.setBackgroundColor(ContextCompat.getColor(this, R.color.mg_bcbcbc))
            }
        }

        binding.btnClear.setOnClickListener {
            val request = RequestConcernsDto(binding.etLetter.text.toString())
            viewModel.onPostConcerns(request)
        }
    }

    private fun onClickClearButton() {
        viewModel.isPostSuccessful.observe(this) { isSuccessful ->
            if (isSuccessful) {
                val intent = Intent(this, LoadingActivity::class.java).apply {
                    putExtra("concerns", binding.etLetter.text.toString())
                }
                startActivity(intent)
            }
        }
    }
}
