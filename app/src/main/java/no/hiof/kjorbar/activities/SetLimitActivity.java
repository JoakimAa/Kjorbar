package no.hiof.kjorbar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TimePicker;

import no.hiof.kjorbar.R;

public class SetLimitActivity extends AppCompatActivity {

    private TimePicker drivableInTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_limit);

        drivableInTime = findViewById(R.id.drivableInTimePicker);
        drivableInTime.setIs24HourView(true);
    }
}