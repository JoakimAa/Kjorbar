package no.hiof.kjorbar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import no.hiof.kjorbar.R;
import no.hiof.kjorbar.adapter.AlcoholUnitRecyclerAdapter;
import no.hiof.kjorbar.datahandler.UpdateDB;
import no.hiof.kjorbar.model.AlcoholUnit;

public class CalculationActivity extends AppCompatActivity {
    private RecyclerView recycleViewUnits;
    private TextView txtPerMillNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        setButtons();
        setTextPerMill();
        setUpRecyclerView();
    }

    private void setButtons() {
        Button btnAddUnit = findViewById(R.id.btnAddUnits);
        txtPerMillNumber = findViewById(R.id.txtPerMillNumber);

        btnAddUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddUnitActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTextPerMill() {
        txtPerMillNumber.setText(String.format(Locale.getDefault(),"%.2f", CalculateActivity.getCalculation().getTotalPerMill(1.0)));
    }

    private void setUpRecyclerView() {
        recycleViewUnits = findViewById(R.id.recycleViewUnits);
        recycleViewUnits.setAdapter(new AlcoholUnitRecyclerAdapter(this, CalculateActivity.getCalculation().getAlcoholUnits(), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recycleViewUnits.getChildAdapterPosition(view);
                AlcoholUnit clickedAlcoholUnit = CalculateActivity.getCalculation().getAlcoholUnits().get(position);
                CalculateActivity.getCalculation().removeAlcoholUnit(clickedAlcoholUnit);
                setUpRecyclerView();
                setTextPerMill();
                UpdateDB.updateActiveCalculationDB();
            }
        }));

        recycleViewUnits.setLayoutManager(new LinearLayoutManager(this));
    }
}