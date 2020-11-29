package no.hiof.kjorbar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;

import no.hiof.kjorbar.R;
import no.hiof.kjorbar.adapter.AlcoholUnitListRecyclerAdapter;
import no.hiof.kjorbar.datahandler.UnitsDataHandler;
import no.hiof.kjorbar.datahandler.UpdateDB;
import no.hiof.kjorbar.model.AlcoholUnit;
import no.hiof.kjorbar.referencehandler.UserDocumentReferenceHandler;

public class AddUnitActivity extends AppCompatActivity {
    private static final String TAG = UnitsDataHandler.class.getSimpleName();
    private RecyclerView recycleViewUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        recycleViewUnits = findViewById(R.id.recycleViewUnitsDB);
        recycleViewUnits.setAdapter(new AlcoholUnitListRecyclerAdapter(this, UnitsDataHandler.getUnits(), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recycleViewUnits.getChildAdapterPosition(view);
                AlcoholUnit clickedAlcoholUnit = UnitsDataHandler.getUnits().get(position);
                CalculateActivity.getCalculation().addAlcoholUnit(clickedAlcoholUnit);
                UpdateDB.updateActiveCalculationDB();
                Intent intent = new Intent(view.getContext(), CalculationActivity.class);
                startActivity(intent);
            }
        }));
        recycleViewUnits.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent = new Intent(this, CalculationActivity.class);
        startActivity(intent);
    }
}