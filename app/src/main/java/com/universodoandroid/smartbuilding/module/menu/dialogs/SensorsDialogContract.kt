package com.universodoandroid.smartbuilding.module.menu.dialogs

import com.universodoandroid.smartbuilding.module.menu.dto.SensorDto

interface SensorsDialogContract {
    interface View {
        fun showSensors(sensors: List<SensorDto>)
        fun showError(error: String)
    }

    interface Handler {
        fun onClose(view: android.view.View)
    }
}