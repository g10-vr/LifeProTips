package firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.util.HashMap;

/**
 * Created by Vishal Rathod on 2016-10-02.
 */
public class initFirebaseRedditDatabase {
    private DatabaseReference ref;

    public initFirebaseRedditDatabase() throws Exception {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(new FileInputStream("C:/Users/Vishal Rathod/Downloads/LifeProTips-c821994647a8.json"))
                .setDatabaseUrl("https://lifeprotips-75424.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);

        this.ref = com.google.firebase.database.FirebaseDatabase
                .getInstance()
                .getReference("threads");
        this.ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public DatabaseReference getReference() {
        return this.ref;
    }

    public void storeThreadsinDatabase(HashMap threads) {
        //System.out.println(threads);
        this.ref.setValue(threads);
    }
}
