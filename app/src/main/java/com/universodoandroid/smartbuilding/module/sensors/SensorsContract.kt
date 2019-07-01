package com.universodoandroid.smartbuilding.module.sensors

import com.universodoandroid.smartbuilding.module.BaseView
import com.universodoandroid.smartbuilding.module.sensors.dto.SensorDto

interface SensorsContract {
    interface View: BaseView {
        fun showSensors(sensors: List<SensorDto>)
        fun showError(error: String)
    }
}