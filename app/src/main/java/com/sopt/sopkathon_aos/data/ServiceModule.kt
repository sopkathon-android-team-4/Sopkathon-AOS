package com.sopt.sopkathon_aos.data

import com.sopt.sopkathon_aos.data.api.LuckyAnswerApi

object ServiceModule {
    val luckyAnswerApi: LuckyAnswerApi by lazy { RetrofitFactory.create<LuckyAnswerApi>() }
}
