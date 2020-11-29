package no.hiof.kjorbar.helper;

import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import no.hiof.kjorbar.model.AlcoholUnit;
import no.hiof.kjorbar.model.Calculation;
import no.hiof.kjorbar.model.User;

public class Dummy {
    public static void populateUnits() {
        AlcoholUnit grevensApple = new AlcoholUnit("Grevens eple", "Grevens", "Cider", 4.7, 5, "dl", 18.0);
        AlcoholUnit grevensPare = new AlcoholUnit("Grevens pære", "Grevens", "Cider", 4.7, 5, "dl", 18.0);
        AlcoholUnit tuborg = new AlcoholUnit("Tuborg", "Tuborg", "Beer", 4.7, 5, "dl", 18.0);
        AlcoholUnit heineken = new AlcoholUnit("Heineken", "Heineken", "Beer", 4.7, 5, "dl", 18.0);
        AlcoholUnit absolute = new AlcoholUnit("Absolute", "Absolute", "Booze", 40.0, 4, "cl", 12.6);

        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        CollectionReference drinksCollectionReference = firestoreDb.collection("units");

        drinksCollectionReference.add(grevensApple);
        drinksCollectionReference.add(grevensPare);
        drinksCollectionReference.add(heineken);
        drinksCollectionReference.add(tuborg);
        drinksCollectionReference.add(absolute);
    }

}
