package com.sopt.sopkathon_aos.data

import com.sopt.sopkathon_aos.data.api.AuthService
import com.sopt.sopkathon_aos.data.api.ConcernsPostApi
import com.sopt.sopkathon_aos.data.api.ConnectInstagramApi
import com.sopt.sopkathon_aos.data.api.LuckyAnswerApi

object ServiceModule {
    val authService: AuthService by lazy { RetrofitFactory.create<AuthService>() }
    val concernsService by lazy { RetrofitFactory.create<ConcernsPostApi>() }
    val connectInstagramApi: ConnectInstagramApi by lazy { RetrofitFactory.create<ConnectInstagramApi>() }
    val luckyAnswerApi: LuckyAnswerApi by lazy { RetrofitFactory.create<LuckyAnswerApi>() }
}