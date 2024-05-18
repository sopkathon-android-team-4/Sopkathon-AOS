package com.sopt.sopkathon_aos.s9hn.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.sopkathon_aos.databinding.ItemLuckyAnswerBinding
import com.sopt.sopkathon_aos.s9hn.mypage.model.LuckyAnswerUiModel


class MyPageViewHolder(
    private val binding: ItemLuckyAnswerBinding,
    onClick: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.onClick = onClick
    }

    fun bind(luckyAnswerUiModel: LuckyAnswerUiModel) {
        binding.luckyAnswerUiModel = luckyAnswerUiModel
    }

    companion object {

        fun of(parent: ViewGroup, onClick: (String) -> Unit): MyPageViewHolder =
            MyPageViewHolder(
                ItemLuckyAnswerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                onClick,
            )
    }
}
