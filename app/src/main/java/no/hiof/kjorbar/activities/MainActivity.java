package no.hiof.kjorbar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import no.hiof.kjorbar.R;
import no.hiof.kjorbar.datahandler.UnitsDataHandler;
import no.hiof.kjorbar.helper.Dummy;
import no.hiof.kjorbar.model.AlcoholUnit;

public class MainActivity extends AppCompatActivity {
    private final int RC_SIGN_IN = 123;
    private Button btnNavToProfile, btnNavToCalculate;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createAuthStateListener();
        setViews();
        setUnitsInUnitsList();
        //Dummy.populateUnits();
    }

    private void setViews() {
        Button button = findViewById(R.id.btnLogOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(getApplicationContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.loged_out), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        btnNavToProfile = findViewById(R.id.btnNavToProfile);
        btnNavToCalculate = findViewById(R.id.btnNavToCalculate);

        btnNavToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnNavToCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CalculateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createAuthStateListener() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if (currentUser == null) {
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());

                    // Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }
                // TODO: Legg inn logget inn som i layout
                /*else {
                    Toast.makeText(getApplicationContext(), "Signed in as " + currentUser.getDisplayName(), Toast.LENGTH_LONG).show();
                }*/
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != RC_SIGN_IN)
            return;

        if (resultCode == RESULT_OK) {
            FirebaseUser currentUser = auth.getCurrentUser();
            assert currentUser != null;
            Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.loged_in_as) + currentUser.getDisplayName(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Signed in cancelled", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        auth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        auth.addAuthStateListener(authStateListener);
    }

    private void setUnitsInUnitsList() {
        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        CollectionReference unitsCollectionReference = firestoreDb.collection("units");

        unitsCollectionReference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                                AlcoholUnit alcoholUnit = document.toObject(AlcoholUnit.class);
                                UnitsDataHandler.addUnit(alcoholUnit);
                            }
                    }}});
    }
}