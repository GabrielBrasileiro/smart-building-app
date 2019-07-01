package com.universodoandroid.smartbuilding

import android.app.Application
import android.content.Intent
import com.universodoandroid.smartbuilding.local.Session
import com.universodoandroid.smartbuilding.module.apartments.ApartmentActivity

class SmartBuildingApplication : Application() {

    companion object {
        var session: Session? = null
        lateinit var instance: SmartBuildingApplication
    }

    override fun onCreate() {
        super.onCreate()
        session = Session(applicationContext)

        session?.isLoggedIn()?.let { isLoggedIn ->
            if (isLoggedIn) {
                val intent = Intent(applicationContext, ApartmentActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }

        instance = this
    }

}