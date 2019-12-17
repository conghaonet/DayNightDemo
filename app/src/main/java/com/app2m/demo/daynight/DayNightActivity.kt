package com.app2m.demo.daynight

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class DayNightActivity : AppCompatActivity() {
    companion object {
        const val MODE_NIGHT_ACTION = "com.app2m.intent.mode_night_action"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_night)
    }
    override fun onResume() {
        super.onResume()
        val defaultNightMode: Int = AppCompatDelegate.getDefaultNightMode()
        Toast.makeText(this, "DefaultNightMode = $defaultNightMode", Toast.LENGTH_SHORT).show()

        val toastMsg = when(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> "UI_MODE_NIGHT_NO(${Configuration.UI_MODE_NIGHT_NO})"
            Configuration.UI_MODE_NIGHT_YES -> "UI_MODE_NIGHT_YES(${Configuration.UI_MODE_NIGHT_YES})"
            Configuration.UI_MODE_NIGHT_UNDEFINED -> "UI_MODE_NIGHT_UNDEFINED(${Configuration.UI_MODE_NIGHT_UNDEFINED})"
            else -> "UI_MODE未知"
        }
        Toast.makeText(this, "UI_MODE = $toastMsg", Toast.LENGTH_SHORT).show()

    }

    fun onClickDay(view: View) {
        switchDayNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    fun onClickNight(view: View) {
        switchDayNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
    fun onClickFollowSystem(view: View) {
        switchDayNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    private fun switchDayNightMode(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(MODE_NIGHT_ACTION))
//        delegate.localNightMode = mode
//        recreate()

    }
    fun openMain2Activity(view: View) {
        startActivity(Intent(this, ComponentsActivity::class.java))
    }


}
