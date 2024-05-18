package com.sopt.sopkathon_aos.data

import com.sopt.sopkathon_aos.data.api.ConcernsPostApi
import com.sopt.sopkathon_aos.data.api.ConnectInstagramApi

object ServiceModule {
    val concernsService by lazy { RetrofitFactory.create<ConcernsPostApi>() }
    val connectInstagramApi: ConnectInstagramApi by lazy { RetrofitFactory.create<ConnectInstagramApi>() }
}