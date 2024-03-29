package com.universodoandroid.smartbuilding.response

data class ApartmentResponse(
    val createdAt: String,
    val floor: Int,
    val id: Int,
    val number: String,
    val sensors: List<Sensor>,
    val updatedAt: String
)