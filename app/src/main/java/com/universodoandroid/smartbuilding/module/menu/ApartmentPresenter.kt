package com.universodoandroid.smartbuilding.module.menu

import android.annotation.SuppressLint
import com.universodoandroid.smartbuilding.module.menu.dto.ApartmentDto
import com.universodoandroid.smartbuilding.remote.services.ApartmentApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class ApartmentPresenter(private val activity: ApartmentContract.Activity,
                         private val apartmentApiDataSource: ApartmentApiDataSource) {

    fun getApartments() {
        apartmentApiDataSource.getApartments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    activity.showApartments(it.map { apartmentResponse ->
                        ApartmentDto(apartmentResponse.id, apartmentResponse.number)
                    })
                }) {
                    activity.showError(it.localizedMessage)
                }
    }

}