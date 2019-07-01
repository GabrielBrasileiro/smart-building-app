package com.universodoandroid.smartbuilding.module.login

import android.annotation.SuppressLint
import com.universodoandroid.smartbuilding.domain.User
import com.universodoandroid.smartbuilding.remote.services.AuthApiDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class LoginPresenter(
    private val view: LoginContract.View,
    private val authApiDataSource: AuthApiDataSource
) {

    fun login(user: User) {
        authApiDataSource.getToken(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.saveToken(it.token)
            }) {
                view.onError(it.localizedMessage)
            }
    }

}