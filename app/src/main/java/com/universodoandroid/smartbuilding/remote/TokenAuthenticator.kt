package com.universodoandroid.smartbuilding.remote

import com.universodoandroid.smartbuilding.SmartBuildingApplication
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val session = SmartBuildingApplication.session
        session?.getToken()?.let {
            return response.request().newBuilder().header("TokenResponse", it).build()
        }
        return null
    }

}