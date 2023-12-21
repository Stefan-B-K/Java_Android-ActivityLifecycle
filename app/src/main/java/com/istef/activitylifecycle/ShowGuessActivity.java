package com.istef.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;

public class ShowGuessActivity extends AppCompatActivity {

    private Intent intent;
    private TextView txtReceived;
    private Button btnOk;
    private Button btnFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        intent = getIntent();

        txtReceived = findViewById(R.id.txtReceived);
        btnOk = findViewById(R.id.buttonOk);
        btnFail = findViewById(R.id.buttonFail);

        txtReceived.setText(intent.getStringExtra("guess"));

        btnOk.setOnClickListener(v -> {
            intent.putExtra("Return Call", "I'll Be Back");
            setResult(MainActivity.SHOW_OK, intent);
            finish();
        });

        btnFail.setOnClickListener(v -> {
            intent.putExtra("Return Call", "Game Over");
            setResult(MainActivity.SHOW_FAIL, intent);
            finish();
        });

    }
}