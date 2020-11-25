package no.hiof.kjorbar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.installations.local.PersistedInstallationEntry;

import no.hiof.kjorbar.R;
import no.hiof.kjorbar.model.Calculation;
import no.hiof.kjorbar.model.User;

public class StartNewCalculationActivity extends AppCompatActivity {

    private Button btnSetLimit;
    private Button bntStartNewCalculation;
    private static Calculation calculation;
    private User user;

    final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore firestoreDb;
    private CollectionReference usersCollectionReference;
    private DocumentReference userDocumentReference;
    private CollectionReference calculationCollectionReference;
    private DocumentReference calculationDocumentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_calculation);


        setFireStoreReferences();
        calculation = new Calculation();
        setCalculationReference();

        btnSetLimit = findViewById(R.id.btnSetLimits);
        bntStartNewCalculation = findViewById(R.id.btnStartNewCalculation);

        btnSetLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SetLimitActivity.class);
                startActivity(intent);
            }
        });

        bntStartNewCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation.setUserGender(user.getGender());
                calculation.setUserWeight(user.getWeight());
                Intent intent = new Intent(view.getContext(), CalculationActivity.class);
                startActivity(intent);
                calculationCollectionReference.document(calculation.getUid()).set(calculation);
            }
        });
    }

    private void setFireStoreReferences() {
        firestoreDb = FirebaseFirestore.getInstance();
        usersCollectionReference = firestoreDb.collection("users");
        assert firebaseUser != null;

        userDocumentReference = usersCollectionReference.document(firebaseUser.getUid());
        setUserDocumentReference();
    }

    private void setCalculationReference() {
        calculationCollectionReference = userDocumentReference.collection("calculations");
        calculationDocumentReference = calculationCollectionReference.document(calculation.getUid());
        calculationDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Calculation calculation = documentSnapshot.toObject(Calculation.class);
            }
        });
    }

    private void setUserDocumentReference() {
        userDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                System.out.println("User reference");
                System.out.println(user);
            }
        });
    }

    private void setCalculationUser() {
        calculation.setUserWeight(user.getWeight());
        calculation.setUserGender(user.getGender());
    }

    public static Calculation getCalculation() {
        return calculation;
    }
}