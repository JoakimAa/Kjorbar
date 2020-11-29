package no.hiof.kjorbar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import no.hiof.kjorbar.R;

public class StartNewCalculationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_calculation);
        setButtons();
    }

    private void setButtons() {
        Button btnSetLimit = findViewById(R.id.btnSetLimits);
        Button btnStartNewCalculation = findViewById(R.id.btnStartNewCalculation);

        btnSetLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SetLimitActivity.class);
                startActivity(intent);
            }
        });

        btnStartNewCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CalculationActivity.class);
                startActivity(intent);
            }
        });
    }





}