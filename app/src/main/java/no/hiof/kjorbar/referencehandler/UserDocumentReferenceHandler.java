package no.hiof.kjorbar.referencehandler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import no.hiof.kjorbar.model.User;

public class UserDocumentReferenceHandler {
    private static final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private static User user;
    private FirebaseFirestore firestoreDb;

    public static DocumentReference getUserDocumentReferenceFromFirestore() {
        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        CollectionReference usersCollectionReference = firestoreDb.collection("users");
        assert firebaseUser != null;
        return usersCollectionReference.document(firebaseUser.getUid());
    }
}
