package com.sopt.sopkathon_aos.hyunjinn.connectInsta

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.sopkathon_aos.data.ServiceModule
import kotlinx.coroutines.launch

class ConnectInstaViewModel : ViewModel() {
    private val _serverText = MutableLiveData<String>()
    val serverText: LiveData<String> = _serverText
    private val _luckyMessage = MutableLiveData<String>()
    val luckyMessage: LiveData<String> = _luckyMessage

    init {
        fetchLuckyMessage(1)
    }

    fun setServerText(text: String) {
        _serverText.value = text
    }

    fun setLuckyText(memberId: Int) {
        fetchLuckyMessage(memberId)
    }

    private fun fetchLuckyMessage(concernId: Int) {
        viewModelScope.launch {
            runCatching {
                ServiceModule.connectInstagramApi.getLuckyContent(concernId)
            }.onSuccess { response ->
                val content = response.data?.content ?: "메시지가 없습니다."
                _luckyMessage.value = content
            }.onFailure { throwable ->
                Log.e("ConnectInstaViewModel", "Failed to fetch lucky message", throwable)
                _luckyMessage.value = "메시지를 가져오는 데 실패했습니다."
            }
        }
    }
}
