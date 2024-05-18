package com.sopt.sopkathon_aos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.sopkathon_aos.arinming.HomeActivity
import com.sopt.sopkathon_aos.databinding.ActivityConnectinstaBinding
import com.sopt.sopkathon_aos.databinding.ActivityMainBinding
import com.sopt.sopkathon_aos.t1nm1ksun.profileinput.ProfileInputActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.vMainBg.setOnClickListener {
            val intent = Intent(this, ProfileInputActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}