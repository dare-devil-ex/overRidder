package com.lonemods.overridder

import android.accessibilityservice.AccessibilityService
import android.content.ComponentName
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent

class KeyService : AccessibilityService() {

    // YouTube keyCode & scanCode
    private val youTube = arrayOf(4065, 245)
    // Netflix keyCode & scanCode
    private val netflix = arrayOf(4062, 242)
    // Prime keyCode & scanCode
    private val spotify = arrayOf(4099, 752)

    private fun launch(packageName: String) {
        try {
            val pm = packageManager

            val intent = Intent(Intent.ACTION_MAIN, null).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
                `package` = packageName
            }

            val resolveInfoList = pm.queryIntentActivities(intent, 0)

            if (resolveInfoList.isNotEmpty()) {
                val activityInfo = resolveInfoList[0].activityInfo
                val launchIntent = Intent(Intent.ACTION_MAIN).apply {
                    component = ComponentName(activityInfo.packageName, activityInfo.name)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(launchIntent)
            } else {
                Log.d("ddex-errorLog", "No launchable activity found for $packageName") // To catch the logs use 'ddex' keyword
            }

        } catch (e: Exception) {
            Log.e("ddex-errorLog", "Cannot launch app: ${e.message}")
        }
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {

        if (event.keyCode == youTube[0] || event.scanCode == youTube[1]) {
            Log.d("daredevilex", "Dev[looper] wkaie!")
            launch("com.youtubetv.lonemods")
            return true
        }

        if (event.keyCode == netflix[0] || event.scanCode == netflix[1]) {
            Log.d("daredevilex", "Dev[looper] wkaie!")
            launch("app.netmirror.newtv")
            return true
        }

        if (event.keyCode == spotify[0] || event.scanCode == spotify[1]) {
            Log.d("daredevilex", "Dev[looper] wkaie!")
            launch("com.spotify.tv.android")
            return true
        }

        return super.onKeyEvent(event)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}
}