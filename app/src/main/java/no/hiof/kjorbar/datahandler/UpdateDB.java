package no.hiof.kjorbar.datahandler;

import com.google.firebase.firestore.DocumentReference;

import no.hiof.kjorbar.activities.CalculateActivity;
import no.hiof.kjorbar.referencehandler.UserDocumentReferenceHandler;

public class UpdateDB {

    public static void updateActiveCalculationDB() {
        DocumentReference userDocumentReference = UserDocumentReferenceHandler.getUserDocumentReferenceFromFirestore();
        userDocumentReference
                .collection("active_calculation")
                .document("active_calculation")
                .set(CalculateActivity.getCalculation());
    }
}
