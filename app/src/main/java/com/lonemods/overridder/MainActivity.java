package com.lonemods.overridder;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import com.google.android.material.switchmaterial.SwitchMaterial;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("wkaie", MODE_PRIVATE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event != null) {
            int keycode = event.getKeyCode();
            int scancode = event.getKeyCode();
            TextView log = findViewById(R.id.log);
            log.setText("Key code: " + keycode + "\t Scan code: " + scancode);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onResume() {
        super.onResume();

        TextView Greets = findViewById(R.id.lmodsGreets);
        Greets.setText(AccessChecker.Greeting());

        SwitchMaterial log = findViewById(R.id.logSwtich);
        TextView logPanel = findViewById(R.id.log);

        SwitchMaterial inputSwitch = findViewById(R.id.inputSwtich);
        View input = findViewById(R.id.inputLayout);

        // Accessibility
        SwitchMaterial access = findViewById(R.id.accessSwitch);
        access.setClickable(true);
        access.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Opening to the settings!", Toast.LENGTH_SHORT).show();
            openSettings(v);
        });

        // Log Panel
        boolean isLogEnabled = prefs.getBoolean("log_enabled", false);
        log.setChecked(isLogEnabled);
        logPanel.setVisibility(isLogEnabled ? View.VISIBLE : View.GONE);

        log.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("log_enabled", isChecked).apply();
            logPanel.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        // Input Panel
        boolean isInputEnabled = prefs.getBoolean("input_enabled", false);
        inputSwitch.setChecked(isInputEnabled);
        input.setVisibility(isInputEnabled ? View.VISIBLE : View.GONE);

        inputSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("input_enabled", isChecked).apply();
            logPanel.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });


        AccessChecker checker = new AccessChecker(this);
        String serviceName = "com.lonemods.overridder/com.lonemods.overridder.KeyService";

        if (checker.isServiceEnabled(serviceName)) {
            access.setChecked(true);
            access.setText("Enabled");
        } else {
            access.setChecked(false);
            access.setText("NOT Enabled, toggle to enable");
        }
    }

    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }
}

