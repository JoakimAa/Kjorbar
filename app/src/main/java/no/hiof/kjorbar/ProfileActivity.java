package no.hiof.kjorbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import no.hiof.kjorbar.model.User;

public class ProfileActivity extends AppCompatActivity {

    //private TextView profileWeight, profileHeight, profileAge, profileGender, profileMaxPerMill, profileMaxPerUnit;
    private EditText inpProfileWeight, inpProfileHeight, inpProfileAge, inpProfileGender, inpProfileMaxPerMill, inpProfileMaxPerUnit;
    // TODO: DatePicker og TimePicker i et vindu
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button btnSave;

    private FirebaseFirestore firestoreDb;
    private CollectionReference usersCollectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent receivedIntent = getIntent();
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firestoreDb = FirebaseFirestore.getInstance();
        usersCollectionReference = firestoreDb.collection("users");

        // TextView
        /*profileWeight = findViewById(R.id.profileWeight);
        profileHeight = findViewById(R.id.profileHeight);
        profileAge = findViewById(R.id.profileAge);
        profileGender = findViewById(R.id.profileGender);
        profileMaxPerMill = findViewById(R.id.profileMaxPerMill);
        profileMaxPerUnit = findViewById(R.id.profileMaxPerUnit);*/

        // EditText
        inpProfileWeight = findViewById(R.id.inpProfileWeight);
        inpProfileHeight = findViewById(R.id.inpProfileHeight);
        inpProfileAge = findViewById(R.id.inpProfileAge);
        inpProfileGender = findViewById(R.id.inpProfileGender);
        inpProfileMaxPerMill = findViewById(R.id.inpProfileMaxPerMill);
        inpProfileMaxPerUnit = findViewById(R.id.inpProfileMaxPerUnit);

        //DatePicker
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert firebaseUser != null;
                User user = new User(firebaseUser.getUid(), Integer.parseInt(inpProfileWeight.getText().toString()), Integer.parseInt(inpProfileHeight.getText().toString()), Integer.parseInt(inpProfileAge.getText().toString()), inpProfileGender.getText().toString());

                firestoreDb.collection("users").document(firebaseUser.getUid()).set(user);


            }
        });


    }

}
