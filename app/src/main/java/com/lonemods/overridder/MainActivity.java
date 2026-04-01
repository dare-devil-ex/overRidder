package com.lonemods.overridder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event != null) {
            int keycode = event.getKeyCode();
            int scancode = event.getKeyCode();
            TextView log = findViewById(R.id.log);
            log.setText("Key code: " + keyCode + "\t Scan code: " + scancode);
        }
        return super.onKeyDown(keyCode, event);
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
            openSettings(v);
        });

        AccessChecker checker = new AccessChecker(this);
        String serviceName = "com.lonemods.overridder/com.lonemods.overridder.KeyService";
        access.setText(checker.isServiceEnabled(serviceName) ? "Enabled" : "NOT Enabled, click here to enable");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }
}

