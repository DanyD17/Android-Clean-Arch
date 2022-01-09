package com.example.danytransfernow.feature.transfer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.danytransfernow.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DashboardFragment.newInstance())
                .commitNow()
        }
    }
}