package com.universodoandroid.smartbuilding.module.menu

import com.universodoandroid.smartbuilding.module.BaseView
import com.universodoandroid.smartbuilding.module.menu.dto.ApartmentDto

interface ApartmentContract {
    interface View: BaseView {
        fun showApartments(apartments: List<ApartmentDto>)
        fun showError(error: String)
    }
}