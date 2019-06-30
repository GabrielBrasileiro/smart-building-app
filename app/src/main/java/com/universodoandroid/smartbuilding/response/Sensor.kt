package com.universodoandroid.smartbuilding.response

data class Sensor(
    val createdAt: String,
    val description: String,
    val id: Int,
    val name: String,
    val pivot: Pivot,
    val updatedAt: String
)