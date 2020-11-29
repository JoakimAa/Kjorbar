package no.hiof.kjorbar.activities;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import no.hiof.kjorbar.R;
import no.hiof.kjorbar.datahandler.UpdateDB;
import no.hiof.kjorbar.referencehandler.UserDocumentReferenceHandler;
import no.hiof.kjorbar.model.User;

public class ProfileActivity extends AppCompatActivity {
    private final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private EditText inpProfileWeight, inpProfileHeight, inpProfileAge, inpProfileGender;
    private DocumentReference userDocumentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setEditTextFields();
        setUserDocumentReference();
    }

    @Override
    protected void onPause() {
        super.onPause();
        User newUser = new User(firebaseUser.getUid(), Integer.parseInt(inpProfileWeight.getText().toString()), Integer.parseInt(inpProfileHeight.getText().toString()), Integer.parseInt(inpProfileAge.getText().toString()), inpProfileGender.getText().toString());
        if (CalculateActivity.getCalculation() != null) {
            CalculateActivity.getCalculation().setUserGender(inpProfileGender.getText().toString());
            CalculateActivity.getCalculation().setUserWeight(Integer.parseInt(inpProfileWeight.getText().toString()));
            UpdateDB.updateActiveCalculationDB();
        }
        userDocumentReference.set(newUser);
    }

    private void setUserDocumentReference() {
        userDocumentReference = UserDocumentReferenceHandler.getUserDocumentReferenceFromFirestore();
        userDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                assert user != null;
                setUserValues(user);
            }
        });
    }

    private void setEditTextFields() {
        inpProfileWeight = findViewById(R.id.inpProfileWeight);
        inpProfileHeight = findViewById(R.id.inpProfileHeight);
        inpProfileAge = findViewById(R.id.inpProfileAge);
        inpProfileGender = findViewById(R.id.inpProfileGender);
    }

    private void setUserValues(User user) {
        if (user != null) {
            inpProfileWeight.setText(String.format("%s", user.getWeight()));
            inpProfileHeight.setText(String.format("%s", user.getHeight()));
            inpProfileAge.setText(String.format("%s", user.getAge()));
            inpProfileGender.setText(user.getGender());
        }
    }

}
