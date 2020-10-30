package no.hiof.kjorbar.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import no.hiof.kjorbar.R;
import no.hiof.kjorbar.model.CalculationLimit;

public class SetLimitActivity extends AppCompatActivity {

    private TimePicker drivableInTime;
    private DatePicker drivableInDate;
    private EditText maxPerMill, maxUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);

        maxPerMill = findViewById(R.id.inpProfileMaxPerMill);
        maxUnit = findViewById(R.id.inpProfileMaxPerUnit);
        drivableInDate = findViewById(R.id.drivableInDatePicker);
        drivableInTime = findViewById(R.id.drivableInTimePicker);
        drivableInTime.setIs24HourView(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
        LocalDate date = LocalDate.of(drivableInDate.getYear(), drivableInDate.getMonth(), drivableInDate.getDayOfMonth());
        LocalTime time = LocalTime.of(drivableInTime.getHour(), drivableInTime.getMinute());
        LocalDateTime drivableInDateTime = LocalDateTime.of(date, time);

        StartNewCalculationActivity.getCalculation().setCalculationLimit(new CalculationLimit(Integer.parseInt(maxPerMill.getText().toString()), Integer.parseInt(maxUnit.getText().toString()), drivableInDateTime));
        System.out.println(StartNewCalculationActivity.getCalculation().toString());

    }
}