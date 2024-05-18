package com.sopt.sopkathon_aos.arinming

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.sopkathon_aos.databinding.ActivityLoadingBinding
import com.sopt.sopkathon_aos.hyunjinn.ConnectInstaActivity

class LoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAnimationListener()
    }


    private fun setAnimationListener() {
        val concerns = intent.getStringExtra("concerns")

        val intent = Intent(this, ConnectInstaActivity::class.java).apply {
            putExtra("concerns", concerns)
        }

        binding.lottie.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {

            }

            override fun onAnimationEnd(p0: Animator) {
                startActivity(intent)
            }

            override fun onAnimationCancel(p0: Animator) {

            }

            override fun onAnimationRepeat(p0: Animator) {

            }
        })
    }
}