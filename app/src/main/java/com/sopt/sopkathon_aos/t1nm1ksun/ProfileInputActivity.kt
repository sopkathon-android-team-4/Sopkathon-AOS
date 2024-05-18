package com.sopt.sopkathon_aos.t1nm1ksun

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import coil.transform.CircleCropTransformation
import com.sopt.sopkathon_aos.R
import com.sopt.sopkathon_aos.arinming.HomeActivity
import com.sopt.sopkathon_aos.data.ServiceModule
import com.sopt.sopkathon_aos.data.request.MemberCreateDto
import com.sopt.sopkathon_aos.data.response.SuccessStatusResponse
import com.sopt.sopkathon_aos.databinding.ActivityProfileinputBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileinputBinding

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                openGallery()
            }
        }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(gallery)
    }

    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let {
                    binding.ivProfile.load(it) {
                        transformations(CircleCropTransformation())
                    }
                    binding.ivProfile
                    binding.ivProfile.clipToOutline
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityProfileinputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivProfile.setOnClickListener {
            checkGalleryPermission()

        }
        checkConditions()
        binding.etNickname.addTextChangedListener(nicknameTextWatcher)

        updateNicknameCount(binding.etNickname.text?.length ?: 0)


        binding.etNickname.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                setNickNameEditTextState(false)
                setEditTextState(binding.etAge, binding.textView2, binding.ivClover02, true)
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.etAge.requestFocus()
                }, 100)
                setEditTextState(binding.etMbti, binding.textView3, binding.ivClover03, false)
                true
            } else {
                false
            }
        }

        binding.etAge.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                if (isInteger(binding.etAge.text.toString())) {
                    setNickNameEditTextState(false)
                    setEditTextState(binding.etAge, binding.textView2, binding.ivClover02, false)
                    setEditTextState(binding.etMbti, binding.textView3, binding.ivClover03, true)
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.etMbti.requestFocus()
                    }, 100)
                    true
                } else {
                    Toast.makeText(this, "나이를 확인해주세요", Toast.LENGTH_SHORT).show()
                    false
                }
            } else {
                false
            }
        }
        binding.etMbti.addTextChangedListener(textWatcher)


    }

    private fun checkGalleryPermission() {
        if (this.checkSelfPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openGallery()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun updateNicknameCount(length: Int) {
        if (length > 0) {
            binding.tvNicknameCount.text = "$length/20"
        }

    }

    private val nicknameTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            updateNicknameCount(s?.length ?: 0)
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (binding.etMbti.text.toString().isNotEmpty()) {
                updateButtonState()
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun updateButtonState() {
        val nickname = binding.etNickname.text.toString().trim()
        val age = binding.etAge.text.toString().trim()
        val mbti = binding.etMbti.text.toString().trim()
        val btn = binding.btnSuccess

        val isAllFilled = nickname.isNotEmpty() && age.isNotEmpty() && mbti.isNotEmpty()

        btn.isEnabled = isAllFilled
        btn.setBackgroundColor(
            if (isAllFilled) Color.BLACK else ContextCompat.getColor(this, R.color.mg_bcbcbc)
        )
        btn.setOnClickListener {
            signUp()

            if (validMBTI(binding.etMbti.text.toString().uppercase())) {
                //다음 화면으로
//                val intent = Intent(this, MainActivity::javaClass)
//                startActivity(intent)
            } else {
                Toast.makeText(this, "MBTI형식을 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun checkConditions() {
        val nicknameText = binding.etNickname.text.toString().trim()
        val ageText = binding.etAge.text.toString().trim()
        val mbtiText = binding.etMbti.text.toString().trim()
        val defaultImage = resources.getDrawable(R.drawable.img_info_camera_default)

        if (binding.ivProfile == defaultImage) {
            //프로필설정
            setNickNameEditTextState(false)
            setEditTextState(binding.etAge, binding.textView2, binding.ivClover02, false)
            setEditTextState(binding.etMbti, binding.textView3, binding.ivClover03, false)
        } else if (nicknameText.isEmpty()) {
            binding.etNickname.requestFocus()
            setNickNameEditTextState(true)
            setEditTextState(binding.etAge, binding.textView2, binding.ivClover02, false)
            setEditTextState(binding.etMbti, binding.textView3, binding.ivClover03, false)
        }
    }

    private fun setEditTextState(
        editText: EditText,
        textView: TextView,
        imageView: ImageView,
        isEnabled: Boolean
    ) {
        editText.isEnabled = isEnabled
        editText.alpha = if (isEnabled) 1.0f else 0.3f
        textView.setTextColor(
            if (isEnabled) Color.BLACK
            else ContextCompat.getColor(this, R.color.mg_bcbcbc)
        )
        imageView.alpha = if (isEnabled) 1.0f else 0.3f
    }

    private fun setNickNameEditTextState(
        isEnabled: Boolean
    ) {

        binding.etNickname.isEnabled = isEnabled
        binding.etNickname.alpha = if (isEnabled) 1.0f else 0.3f
        binding.tvNicknameCount.setTextColor(
            if (isEnabled) Color.BLACK
            else ContextCompat.getColor(this, R.color.mg_bcbcbc)
        )

        binding.textView.setTextColor(
            if (isEnabled) Color.BLACK
            else ContextCompat.getColor(this, R.color.mg_bcbcbc)
        )
        binding.ivClover01.alpha = if (isEnabled) 1.0f else 0.3f
    }

    private fun signUp() {
        val memberCreateRequest = getCreateMemberRequestDto()

        ServiceModule.authService.postMember(memberCreateRequest)
            .enqueue(object : Callback<SuccessStatusResponse> {
                override fun onResponse(
                    call: Call<SuccessStatusResponse>,
                    response: Response<SuccessStatusResponse>,
                ) {

                    Log.d("SignUp", response.toString())
                    if (response.isSuccessful) {
                        val data: SuccessStatusResponse? = response.body()
                        val memberId = data?.data?.memberId

                        Log.d("SignUp", "data: $data, userId: $memberId")
                        val intent = Intent(this@ProfileInputActivity, HomeActivity::class.java)
                        intent.putExtra("memberId", memberId)

                        startActivity(intent)

                    } else {
                        Log.d("SignUp123123", response.toString())
                        val errorC = response.code()
                        val errorM = response.message()
                        Log.d("CreateMEmber", "회원가입 실패 $errorC , $errorM")
                    }
                }

                override fun onFailure(call: Call<SuccessStatusResponse>, t: Throwable) {
                    Log.d("123123", t.toString())
                }
            })
    }

    private fun getCreateMemberRequestDto(): MemberCreateDto {
        val name = binding.etNickname.text.toString()
        val age = binding.etAge.text.toString().toInt()
        val mbti = binding.etMbti.text.toString()
        return MemberCreateDto(
            name = name,
            age = age,
            mbti = mbti
        )
    }


    fun validMBTI(mbti: String): Boolean {
        val validMBTIPattern = Regex("[EI][SN][TF][JP]")
        return validMBTIPattern.matches(mbti)
    }

    fun isInteger(age: String): Boolean {
        val regex = "^(?:[1-9]|[1-9][0-9])$".toRegex()
        return regex.matches(age)
    }


}

