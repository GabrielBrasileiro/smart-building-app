package com.universodoandroid.smartbuilding.remote.services

import com.universodoandroid.smartbuilding.response.ApartmentResponse
import com.universodoandroid.smartbuilding.response.ApartmentsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApartmentApiDataSource {

    @GET("/api/apartments")
    fun getApartments(): Observable<List<ApartmentsResponse>>

    @GET("/api/apartment/{id}")
    fun getApartment(@Path("id") id: String): Observable<ApartmentResponse>

}