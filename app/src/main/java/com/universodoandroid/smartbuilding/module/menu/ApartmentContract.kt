package com.universodoandroid.smartbuilding.module.menu

import com.universodoandroid.smartbuilding.module.menu.dto.ApartmentDto

interface ApartmentContract {
    interface Activity {
        fun showApartments(apartments: List<ApartmentDto>)
        fun showError(error: String)
    }
}