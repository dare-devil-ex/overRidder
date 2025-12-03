package com.lonemods.overridder

import android.accessibilityservice.AccessibilityService
import android.content.ComponentName
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent

class KeyService : AccessibilityService() {

    private fun launch(packageName: String) {
        try {
            val pm = packageManager

            val intent = Intent(Intent.ACTION_MAIN, null).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
                `package` = packageName
            }

            val resolveInfoList = pm.queryIntentActivities(intent, 0)

            if (resolveInfoList.isNotEmpty()) {
                // Pick the first activity
                val activityInfo = resolveInfoList[0].activityInfo
                val launchIntent = Intent(Intent.ACTION_MAIN).apply {
                    component = ComponentName(activityInfo.packageName, activityInfo.name)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(launchIntent)
            } else {
                Log.d("ddex-errorLog", "No launchable activity found for $packageName")
            }

        } catch (e: Exception) {
            Log.e("ddex-errorLog", "Cannot launch app: ${e.message}")
        }
    }



    override fun onKeyEvent(event: KeyEvent): Boolean {

        if (event.keyCode == 4065 || event.scanCode == 245) {
            Log.d("daredevilex", "Dev[looper] wkaie!")
            launch("com.youtubetv.lonemods")
            return true
        }

        if (event.keyCode == 4062 || event.scanCode == 242) {
            Log.d("daredevilex", "Dev[looper] wkaie!")
            launch("app.netmirror.newtv")
            return true
        }

        if (event.keyCode == 4099 || event.scanCode == 752) {
            Log.d("daredevilex", "Dev[looper] wkaie!")
            launch("com.saikou.sozo_tv")
            return true
        }

        return super.onKeyEvent(event)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}
}

class Wkaie() {
    companion object {
        @JvmStatic
        val greets = listOf("You’ve got this.",
            "Keep going — progress is progress, no matter how small.",
            "One step at a time.",
            "Don’t stop now; you’re closer than you think.",
            "You’re stronger than your excuses.",
            "The only way out is through.",
            "Small daily actions lead to big results.",
            "Your future self is cheering for you.",
            "You don’t have to be perfect — just consistent.",
            "Believe in the work you're putting in.").random()
    }
}