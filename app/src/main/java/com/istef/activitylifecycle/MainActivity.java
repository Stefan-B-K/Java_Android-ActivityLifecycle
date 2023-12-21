package com.istef.activitylifecycle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static androidx.activity.result.contract.ActivityResultContracts.*;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private Button btnGuess;
    private EditText txtGuess;
    public static final int SHOW_OK = 200;
    public static final int SHOW_FAIL = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuess = findViewById(R.id.buttonGuess);
        btnGuess.setEnabled(false);
        txtGuess = findViewById(R.id.txtGuess);

        txtGuess.addTextChangedListener(this);
        btnGuess.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShowGuessActivity.class);
            intent.putExtra("guess", txtGuess.getText().toString().trim());
            activityResultLauncher.launch(intent);
        });
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Intent data = result.getData();
                String message = data.getStringExtra("Return Call");
                if (result.getResultCode() == SHOW_OK) {
                    Toast.makeText(this, "OK: " + message, Toast.LENGTH_SHORT).show();
                }
                if (result.getResultCode() == SHOW_FAIL) {
                    Toast.makeText(this, "FAIL: " + message, Toast.LENGTH_SHORT).show();
                }

            });

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        btnGuess.setEnabled(!txtGuess.getText().toString().trim().isEmpty());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

}