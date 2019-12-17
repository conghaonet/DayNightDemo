package com.app2m.demo.daynight

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    private val toolbar by lazy {
        findViewById<MaterialToolbar>(R.id.home_tool_bar)
    }
    private val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.home_drawer_layout)
    }
    private val dayNightReceiver by lazy {
        DayNightReceiver()
    }
    private lateinit var mDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LocalBroadcastManager.getInstance(this).registerReceiver(dayNightReceiver, IntentFilter(DayNightActivity.MODE_NIGHT_ACTION))

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name)

        mDrawerToggle.syncState()
        drawerLayout.addDrawerListener(mDrawerToggle)
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.dayNightReceiver)
        super.onDestroy()

    }

    fun openDayNightActivity(view: View) {
        startActivity(Intent(this, DayNightActivity::class.java))
    }

    inner class DayNightReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
//            val defaultNightMode: Int = AppCompatDelegate.getDefaultNightMode()
//            Toast.makeText(context, "MainActivity DefaultNightMode = $defaultNightMode", Toast.LENGTH_SHORT).show()
            recreate()
        }
    }
}
