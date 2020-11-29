package no.hiof.kjorbar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import no.hiof.kjorbar.R;
import no.hiof.kjorbar.datahandler.UnitsDataHandler;
import no.hiof.kjorbar.model.AlcoholUnit;
import no.hiof.kjorbar.model.Calculation;
import no.hiof.kjorbar.model.User;
import no.hiof.kjorbar.referencehandler.UserDocumentReferenceHandler;

public class CalculateActivity extends AppCompatActivity {
    private User user;
    private static Calculation calculation;
    private DocumentReference userDocumentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        setUserDocumentReference();
        setActiveCalculation();
        setButtons();
    }

    private void setActiveCalculation() {
        DocumentReference calculationDocumentReference = userDocumentReference
                .collection("active_calculation")
                .document("active_calculation");
        calculationDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                calculation = documentSnapshot.toObject(Calculation.class);
            }
        });
    }

    private void setButtons() {
        Button btnPreviousCalculations = findViewById(R.id.btnPreviousCalculations);
        Button btnNewCalculation = findViewById(R.id.btnNewCalculation);
        Button btnActiveCalculation = findViewById(R.id.btnActiveCalculation);

        btnPreviousCalculations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PreviousCalculations.class);
                startActivity(intent);
            }
        });

        btnNewCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calculation != null) {
                    userDocumentReference.collection("archived_calculation").add(calculation);
                }
                calculation = new Calculation();
                calculation.setUserGender(user.getGender());
                calculation.setUserWeight(user.getWeight());
                userDocumentReference
                        .collection("active_calculation")
                        .document("active_calculation")
                        .set(calculation);
                Intent intent = new Intent(view.getContext(), StartNewCalculationActivity.class);
                startActivity(intent);
            }
        });

            btnActiveCalculation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DocumentReference calculationDocumentReference = userDocumentReference
                            .collection("active_calculation")
                            .document("active_calculation");
                    calculationDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            calculation = documentSnapshot.toObject(Calculation.class);
                        }
                    });

                    if (calculation != null) {
                        Intent intent = new Intent(view.getContext(), CalculationActivity.class);
                        startActivity(intent);
                    }
                }
            });

    }

    private void setUserDocumentReference() {
        userDocumentReference = UserDocumentReferenceHandler.getUserDocumentReferenceFromFirestore();
        userDocumentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
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

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setButtons();
    }

    private void addUnitsToCalculation() {
        for (AlcoholUnit unit : UnitsDataHandler.getUnits()) {
            calculation.addAlcoholUnit(unit);
        }
    }
}