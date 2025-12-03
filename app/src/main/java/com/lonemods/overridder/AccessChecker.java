package com.lonemods.overridder;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.List;
import java.util.Random;

public class AccessChecker {

    private final Context context;

    public AccessChecker(Context context) {
        this.context = context;
    }

    public static String Greeting() {
        List<String> greets = List.of(
                "You’ve got this.",
                "Keep going — progress is progress, no matter how small.",
                "One step at a time.",
                "Don’t stop now; you’re closer than you think.",
                "You’re stronger than your excuses.",
                "The only way out is through.",
                "Small daily actions lead to big results.",
                "Your future self is cheering for you.",
                "You don’t have to be perfect — just consistent.",
                "Believe in the work you're putting in."
        );

        Random random = new Random();
        return greets.get(random.nextInt(greets.size()));
    }

    public boolean isServiceEnabled(String serviceFullName) {
        try {
            int accessibilityEnabled = Settings.Secure.getInt(
                    context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED
            );

            if (accessibilityEnabled != 1) {
                return false;
            }

            String enabledServices = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            );

            if (!TextUtils.isEmpty(enabledServices)) {
                String[] services = enabledServices.split(":");
                for (String service : services) {
                    if (service.equalsIgnoreCase(serviceFullName)) {
                        return true;
                    }
                }
            }
        } catch (Settings.SettingNotFoundException ignored) {}

        return false;
    }
}

