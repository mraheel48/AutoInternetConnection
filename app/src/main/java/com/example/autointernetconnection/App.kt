package com.example.autointernetconnection

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Process

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (isMainProcess()) {
            ConnectivityProvider.createProvider(this).subscribe()
        }
    }

    // your package name is the same with your main process name
    private fun isMainProcess(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageName == getProcessName()
        } else packageName == getProcessNameLegacy()
    }

    // you can use this method to get current process name, you will get
    private fun getProcessNameLegacy(): String? {
        val myPid = Process.myPid()
        val manager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val infos = manager.runningAppProcesses
        for (info in infos) {
            if (info.pid == myPid) {
                return info.processName
            }
        }
        // may never return null
        return null
    }
}