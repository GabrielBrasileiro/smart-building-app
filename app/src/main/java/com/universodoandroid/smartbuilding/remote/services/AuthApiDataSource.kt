package com.universodoandroid.smartbuilding.remote.services

import com.universodoandroid.smartbuilding.domain.User
import com.universodoandroid.smartbuilding.response.TokenResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiDataSource {

    @POST("auth/login")
    fun getToken(@Body user: User): Observable<TokenResponse>

}