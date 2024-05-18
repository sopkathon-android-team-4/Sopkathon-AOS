package com.sopt.sopkathon_aos.data

import RetrofitFactory
import com.sopt.sopkathon_aos.data.api.ConnectInstagramApi

object ServiceModule {
    val connectInstagramApi: ConnectInstagramApi by lazy { RetrofitFactory.create<ConnectInstagramApi>() }
}