package com.lonemods.overridder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView Greets = findViewById(R.id.lmodsGreets);
        Greets.setText(Wkaie.getGreets());
    }

}
