package no.hiof.kjorbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btnNavToProfile, btnNavToCalculate;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        btnNavToProfile = findViewById(R.id.btnNavToProfile);
        btnNavToCalculate = findViewById(R.id.btnNavToCalculate);

        btnNavToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnNavToCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(view.getContext(), CalculateActivity.class);
                startActivity(intent);
            }
        });
    }
}