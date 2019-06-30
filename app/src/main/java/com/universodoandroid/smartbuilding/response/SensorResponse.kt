package com.universodoandroid.smartbuilding.response

data class SensorResponse(
        val createdAt: String,
        val description: String,
        val id: Int,
        val name: String,
        val pivotResponse: PivotResponse,
        val updated_at: String
)