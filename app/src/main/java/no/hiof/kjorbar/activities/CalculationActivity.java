package no.hiof.kjorbar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import no.hiof.kjorbar.R;

public class CalculationActivity extends AppCompatActivity {

    private Button btnAddUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        btnAddUnit = findViewById(R.id.btnAddUnits);

        btnAddUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddUnitActivity.class);
                startActivity(intent);
            }
        });
    }
}