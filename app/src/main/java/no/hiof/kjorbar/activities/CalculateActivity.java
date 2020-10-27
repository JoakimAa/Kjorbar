package no.hiof.kjorbar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import no.hiof.kjorbar.R;

public class CalculateActivity extends AppCompatActivity {
    private Button btnPreviousCalculations;
    private Button btnNewCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        btnPreviousCalculations = findViewById(R.id.btnPreviousCalculations);
        btnNewCalculation = findViewById(R.id.btnNewCalculation);

        btnPreviousCalculations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PreviousCalculations.class);
                startActivity(intent);
            }
        });

        btnNewCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), StartNewCalculationActivity.class);
                startActivity(intent);
            }
        });
    }
}