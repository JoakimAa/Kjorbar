package no.hiof.kjorbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import no.hiof.kjorbar.model.User;

public class ProfileActivity extends AppCompatActivity {
    final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private EditText inpProfileWeight, inpProfileHeight, inpProfileAge, inpProfileGender, inpProfileMaxPerMill, inpProfileMaxPerUnit;
    // TODO: DatePicker og TimePicker i et vindu
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button btnSave;
    private FirebaseFirestore firestoreDb;
    private CollectionReference usersCollectionReference;
    private DocumentReference userDocumentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setEditTextFields();
        setFireStoreReferences();
        setOnclickListenerOnSaveButton();
    }

    private void setOnclickListenerOnSaveButton() {
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = new User(firebaseUser.getUid(), Integer.parseInt(inpProfileWeight.getText().toString()), Integer.parseInt(inpProfileHeight.getText().toString()), Integer.parseInt(inpProfileAge.getText().toString()), inpProfileGender.getText().toString());
                usersCollectionReference.document(firebaseUser.getUid()).set(newUser);
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.changes_have_been_saved), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setFireStoreReferences() {
        firestoreDb = FirebaseFirestore.getInstance();
        usersCollectionReference = firestoreDb.collection("users");
        assert firebaseUser != null;
        userDocumentReference = usersCollectionReference.document(firebaseUser.getUid());
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
        inpProfileMaxPerMill = findViewById(R.id.inpProfileMaxPerMill);
        inpProfileMaxPerUnit = findViewById(R.id.inpProfileMaxPerUnit);
    }

    private void setUserValues(User user) {
        inpProfileWeight.setText(String.format("%s", user.getWeight()));
        inpProfileHeight.setText(String.format("%s", user.getHeight()));
        inpProfileAge.setText(String.format("%s", user.getAge()));
        inpProfileGender.setText(user.getGender());
    }

}
