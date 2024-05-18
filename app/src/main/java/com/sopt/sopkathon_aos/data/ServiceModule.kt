package com.sopt.sopkathon_aos.data

import com.sopt.sopkathon_aos.data.api.AuthService

object ServiceModule {
    val authService: AuthService by lazy { RetrofitFactory.create<AuthService>() }
}