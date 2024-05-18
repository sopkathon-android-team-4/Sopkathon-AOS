package com.sopt.sopkathon_aos.data

import com.sopt.sopkathon_aos.data.api.ConcernsPostApi

object ServiceModule {
    val concernsService by lazy { RetrofitFactory.create<ConcernsPostApi>()}
}