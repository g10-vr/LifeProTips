package firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.Task;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

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
                .getReference("subreddit");
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

    public void storeThreadsinDatabase(HashMap threads) throws Exception {
        final CountDownLatch sync = new CountDownLatch(1);
        DatabaseReference threadsRef = ref.child("threads");
        threadsRef.setValue(threads)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(Task<Void> task) {
                        sync.countDown();
                    }
                });
        sync.await(); //Wait until storing data in database is complete
    }

    public void storeThreadID(String threadID) throws Exception {
        final CountDownLatch sync = new CountDownLatch(1);
        DatabaseReference threadsRef = ref.child("thread_id");
        threadsRef.push().setValue(threadID)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(Task<Void> task) {
                        sync.countDown();
                    }
                });
        sync.await(); //Wait until storing data in database is complete
    }
}
