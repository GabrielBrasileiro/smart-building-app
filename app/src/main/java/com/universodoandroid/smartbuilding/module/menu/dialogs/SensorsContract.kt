package com.universodoandroid.smartbuilding.module.menu.dialogs

import com.universodoandroid.smartbuilding.module.menu.dto.SensorDto

interface SensorsContract {
    interface View {
        fun showSensors(sensors: List<SensorDto>)
        fun showError(error: String)
    }
}