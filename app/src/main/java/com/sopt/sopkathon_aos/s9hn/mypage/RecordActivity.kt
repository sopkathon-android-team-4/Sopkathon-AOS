package com.sopt.sopkathon_aos.s9hn.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.sopkathon_aos.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvConnectinstaNext.setOnClickListener {
            finish()
        }
    }
}