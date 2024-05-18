package com.sopt.sopkathon_aos.s9hn.mypage.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.sopkathon_aos.s9hn.mypage.model.LuckyAnswerUiModel

class MyPageAdapter(
    private val onClick: (String) -> Unit,
) : RecyclerView.Adapter<MyPageViewHolder>() {
    private val items = mutableListOf<LuckyAnswerUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder =
        MyPageViewHolder.of(parent, onClick)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(list: List<LuckyAnswerUiModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}
