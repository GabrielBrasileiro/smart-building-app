package com.universodoandroid.smartbuilding.response

data class Pivot(
    val apartmentId: Int,
    val createdAt: String,
    val isOn: Int,
    val room: Any?,
    val sensorId: Int,
    val updatedAt: String
)