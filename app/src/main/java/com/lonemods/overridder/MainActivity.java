package com.lonemods.overridder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean isAccessibilityServiceEnabled(Context context, Class<MainActivity> service) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED
            );
        } catch (Settings.SettingNotFoundException e) {
            return false;
        }

        if (accessibilityEnabled == 1) {
            String enabledServices = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            );
            if (enabledServices != null) {
                TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
                colonSplitter.setString(enabledServices);

                ComponentName expectedComponentName = new ComponentName(context, service);

                while (colonSplitter.hasNext()) {
                    String enabledService = colonSplitter.next();
                    if (enabledService.equalsIgnoreCase(expectedComponentName.flattenToString())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView Greets = findViewById(R.id.lmodsGreets);
        Greets.setText(Wkaie.getGreets());
        TextView access = findViewById(R.id.access);
        access.setClickable(true);

        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Opening to the settings!", Toast.LENGTH_SHORT).show();
                openSettings();
            }
        });


        if (isAccessibilityServiceEnabled(this, MainActivity.class)) {
            access.setText("Enabled");

        } else {
            access.setText("NOT ENABLED, click here to enable");
        }
    }

    private void openSettings() {
        Intent intent = new Intent("android.settings.SETTINGS");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}

