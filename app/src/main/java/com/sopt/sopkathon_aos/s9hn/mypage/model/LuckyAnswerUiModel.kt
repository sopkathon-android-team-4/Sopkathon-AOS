package com.sopt.sopkathon_aos.s9hn.mypage.model

import com.sopt.sopkathon_aos.R
import com.sopt.sopkathon_aos.data.response.LuckyAnswerResponseDto
import java.util.Random

sealed interface MyPageUiState {
    data class Success(val data: List<LuckyAnswerUiModel>) : MyPageUiState
    data object Failure : MyPageUiState
    data object Idle : MyPageUiState
}

data class LuckyAnswerUiModel(
    val content: String,
) {
    private val folders: List<Int> = listOf(
        R.drawable.img_my_folder_yellow,
        R.drawable.img_my_folder_blue,
        R.drawable.img_my_folder_green,
        R.drawable.img_my_folder_pink,
        R.drawable.img_my_folder_yellow,
    )

    val randomFolder: Int
        get() = getRandomElement(folders)


    private fun getRandomElement(list: List<Int>): Int {
        val random = Random()
        val randomIndex = random.nextInt(list.size)
        return list[randomIndex]
    }
}

fun LuckyAnswerResponseDto.Answer.toPresentation(): LuckyAnswerUiModel = LuckyAnswerUiModel(
    content = content
)
