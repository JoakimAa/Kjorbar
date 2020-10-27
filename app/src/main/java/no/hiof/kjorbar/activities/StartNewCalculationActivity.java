package no.hiof.kjorbar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.installations.local.PersistedInstallationEntry;

import no.hiof.kjorbar.R;

public class StartNewCalculationActivity extends AppCompatActivity {

    private Button btnSetLimit;
    private Button bntStartNewCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_calculation);

        btnSetLimit = findViewById(R.id.btnSetLimits);
        bntStartNewCalculation = findViewById(R.id.btnStartNewCalculation);

        btnSetLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SetLimitActivity.class);
                startActivity(intent);
            }
        });

        bntStartNewCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CalculationActivity.class);
                startActivity(intent);
            }
        });


    }
}