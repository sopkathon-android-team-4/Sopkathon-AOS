package com.sopt.sopkathon_aos.s9hn.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sopt.sopkathon_aos.databinding.ActivityMyPageBinding
import com.sopt.sopkathon_aos.s9hn.mypage.adapter.MyPageAdapter
import com.sopt.sopkathon_aos.s9hn.mypage.model.MyPageUiState

class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding
    private val viewModel: MyPageViewModel by viewModels()
    private val myPageAdapter by lazy { MyPageAdapter(::navigateToConnectInstagram) }

    private fun navigateToConnectInstagram(content: String) {
        val intent = Intent(this, RecordActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        observeUiState()
    }

    private fun initView() {
        binding.rvMyPage.adapter = myPageAdapter
        binding.rvMyPage.setHasFixedSize(true)
    }

    private fun observeUiState() {
        viewModel.uiState.observe(this) {
            when (it) {
                is MyPageUiState.Success -> myPageAdapter.submitList(it.data)
                is MyPageUiState.Failure -> Log.d("FAIL", "FAIL")
                is MyPageUiState.Idle -> Unit
            }
        }
    }
}
