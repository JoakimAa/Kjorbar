package no.hiof.kjorbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileWeight, profileHeight, profileAge, profileGender, profileMaxPerMill, profileMaxPerUnit;
    private EditText inpProfileWeight, inpProfileHeight, inpProfileAge, inpProfileGender, inpProfileMaxPerMill, inpProfileMaxPerUnit;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent receivedIntent = getIntent();

        // TextView
        profileWeight = findViewById(R.id.profileWeight);
        profileHeight = findViewById(R.id.profileHeight);
        profileAge = findViewById(R.id.profileAge);
        profileGender = findViewById(R.id.profileGender);
        profileMaxPerMill = findViewById(R.id.profileMaxPerMill);
        profileMaxPerUnit = findViewById(R.id.profileMaxPerUnit);

        // EditText
        inpProfileWeight = findViewById(R.id.inpProfileWeight);
        inpProfileHeight = findViewById(R.id.inpProfileHeight);
        inpProfileAge = findViewById(R.id.inpProfileAge);
        inpProfileGender = findViewById(R.id.inpProfileGender);
        inpProfileMaxPerMill = findViewById(R.id.inpProfileMaxPerMill);
        inpProfileMaxPerUnit = findViewById(R.id.inpProfileMaxPerUnit);

        //DatePicker
        datePicker = findViewById(R.id.datePicker);


    }

}
