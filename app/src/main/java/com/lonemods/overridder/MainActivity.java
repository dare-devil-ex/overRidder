package com.lonemods.overridder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView Greets = findViewById(R.id.lmodsGreets);
        TextView access = findViewById(R.id.access);
        Greets.setText(AccessChecker.Greeting());
        access.setClickable(true);

        access.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Opening to the settings!", Toast.LENGTH_SHORT).show();
            openSettings();
        });

        AccessChecker checker = new AccessChecker(this);
        String serviceName = "com.lonemods.overridder/com.lonemods.overridder.KeyService";
        access.setText(checker.isServiceEnabled(serviceName) ? "Enabled" : "Service is NOT Enabled.");
    }

    private void openSettings() {
        Intent intent = new Intent("android.settings.SETTINGS");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}

