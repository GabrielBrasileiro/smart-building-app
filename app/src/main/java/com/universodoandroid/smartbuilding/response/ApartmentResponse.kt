package com.universodoandroid.smartbuilding.response

data class ApartmentResponse(
        val created_at: String,
        val floor: Int,
        val id: Int,
        val number: String,
        val sensorResponses: List<SensorResponse>,
        val updated_at: String
)