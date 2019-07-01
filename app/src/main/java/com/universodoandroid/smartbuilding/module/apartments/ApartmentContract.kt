package com.universodoandroid.smartbuilding.module.apartments

import com.universodoandroid.smartbuilding.module.BaseView
import com.universodoandroid.smartbuilding.module.apartments.dto.ApartmentDto

interface ApartmentContract {
    interface View: BaseView {
        fun showApartments(apartments: List<ApartmentDto>)
        fun showError(error: String)
    }
}