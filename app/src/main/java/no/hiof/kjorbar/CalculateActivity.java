package no.hiof.kjorbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CalculateActivity extends AppCompatActivity {
    private Button btnAddUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

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