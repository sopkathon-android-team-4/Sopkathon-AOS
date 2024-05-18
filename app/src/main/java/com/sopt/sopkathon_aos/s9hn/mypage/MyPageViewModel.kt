package com.sopt.sopkathon_aos.s9hn.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.sopkathon_aos.data.ServiceModule
import com.sopt.sopkathon_aos.s9hn.mypage.model.MyPageUiState
import com.sopt.sopkathon_aos.s9hn.mypage.model.toPresentation
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val _uiState: MutableLiveData<MyPageUiState> =
        MutableLiveData<MyPageUiState>(MyPageUiState.Idle)
    val uiState: LiveData<MyPageUiState> = _uiState

    init {
        viewModelScope.launch {
            runCatching {
                ServiceModule.luckyAnswerApi.getLuckyAnswers()
            }.onSuccess { result ->
                _uiState.value = MyPageUiState.Success(result.data.map { it.toPresentation() })
            }.onFailure {
                Log.d("FAIL", it.toString())
            }
        }
    }
}
