package com.sopt.sopkathon_aos.hyunjinn.ConnectInsta

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import com.sopt.sopkathon_aos.databinding.ActivityConnectinstaBinding
import java.io.File
import java.io.FileOutputStream

class ConnectInstaActivity : AppCompatActivity() {
    private val binding by lazy { ActivityConnectinstaBinding.inflate(layoutInflater) }
    private val viewModel: ConnectInstaViewModel by viewModels()

    private val shareActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvConnecinstaShare.setOnClickListener { shareInstagram() }


        // Observer 설정
        viewModel.serverText.observe(this, Observer { user ->
            updateUI("럭키 데이지") // 사용자 데이터 변경 시 UI 업데이트
        })
        // 서버에서 받아온 텍스트
        val serverText = ""

        // 서버에서 받아온 텍스트를 UI에 업데이트
        viewModel.luckyMessage.observe(this, Observer { message ->
            binding.tvConnectinstaLuckymessage.text = message.toString()
        })
        viewModel.setServerText(serverText)
        viewModel.setLuckyText(1)
    }

    private fun updateUI(serverText: String) {
        with(binding) {
            tvConnectinstaName.text = serverText
        }

        // 텍스트뷰의 가로 길이를 측정
        binding.tvConnectinstaName.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        val textViewWidth = binding.tvConnectinstaName.measuredWidth

        // 배경 뷰의 가로 길이 조절
        val layoutParams = binding.vConnectinstaNameBg.layoutParams
        layoutParams.width = textViewWidth + 20
        binding.vConnectinstaNameBg.layoutParams = layoutParams
    }

    private fun shareInstagram() {
        // 텍스트뷰와 이미지뷰를 숨기고, 공유 후에 다시 보이도록 설정
        binding.tvConnecinstaShare.visibility = View.INVISIBLE
        binding.ivConnecinstaShareBg.visibility = View.INVISIBLE
        binding.vConnectinstaAddBg.visibility = View.INVISIBLE
        binding.ivConnectinstaAdd.visibility = View.INVISIBLE
        binding.btMypageTonext.visibility = View.INVISIBLE
        binding.tvConnectinstaNext.visibility = View.INVISIBLE

        // 화면 캡쳐 및 공유 로직은 그대로 유지됩니다.
        val rootView = window.decorView.rootView
        val bitmap = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        rootView.draw(canvas)

        binding.tvConnecinstaShare.visibility = View.VISIBLE
        binding.ivConnecinstaShareBg.visibility = View.VISIBLE
        binding.vConnectinstaAddBg.visibility = View.VISIBLE
        binding.ivConnectinstaAdd.visibility = View.VISIBLE
        binding.btMypageTonext.visibility = View.VISIBLE
        binding.tvConnectinstaNext.visibility = View.VISIBLE

        val imageUri = convertBitmapToImageUri(bitmap)
        if (imageUri != null) {
            val intent = Intent(INSTAGRAM_PATH).apply {
                setDataAndType(imageUri, MEDIA_TYPE_JPEG)
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            try {
                shareActivityResultLauncher.launch(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "인스타그램 앱이 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun convertBitmapToImageUri(bitmap: Bitmap): Uri? {
        val tempFile = File(cacheDir, IMAGE_FORMAT)

        runCatching {
            FileOutputStream(tempFile).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
            }
        }.onFailure {
            Log.e("error", it.message.toString())
        }

        return FileProvider.getUriForFile(
            this,
            "$packageName$URI_FORMAT",
            tempFile,
        )
    }

    companion object {
        private const val INSTAGRAM_PATH = "com.instagram.share.ADD_TO_STORY"
        private const val IMAGE_FORMAT = "temp_image.jpg"
        private const val MEDIA_TYPE_JPEG = "image/jpeg"
        private const val URI_FORMAT = ".fileprovider"
    }
}
