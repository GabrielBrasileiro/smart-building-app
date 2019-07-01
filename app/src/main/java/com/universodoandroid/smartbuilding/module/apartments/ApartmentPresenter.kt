package com.universodoandroid.smartbuilding.module.apartments

import android.annotation.SuppressLint
import com.universodoandroid.smartbuilding.module.apartments.dto.ApartmentDto
import com.universodoandroid.smartbuilding.remote.services.ApartmentApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class ApartmentPresenter(private val view: ApartmentContract.View,
                         private val apartmentApiDataSource: ApartmentApiDataSource) {

    fun getApartments() {
        view.showLoader()
        apartmentApiDataSource.getApartments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showApartments(it.map { apartmentResponse ->
                        ApartmentDto(apartmentResponse.id, apartmentResponse.number)
                    })
                    view.dismissLoader()
                }) {
                    view.showError(it.localizedMessage)
                    view.dismissLoader()
                }
    }

}