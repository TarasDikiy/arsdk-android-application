package com.ntu.arapplication.activitycore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.arapplication.R;
import com.ntu.arapplication.dbmanager.DataModel;

import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class MainActivity extends AppCompatActivity {

    Button btnPlay;

    private final String Appid = "arsdk-application-dxvxt";
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> mongoCollection;

    ArrayList<Double> latitude;
    ArrayList<Double> longitude;
    ArrayList<Integer> radius;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.btnPlay);
        latitude = new ArrayList<>();
        longitude = new ArrayList<>();
        radius = new ArrayList<>();

        Permissions permissions = new Permissions();
        permissions.camPermissions(this, this);
        permissions.locationPermission(this, this);

        Realm.init(this);
        App app = new App(new AppConfiguration.Builder(Appid)
                .build());
        Credentials credentials = Credentials.anonymous();
        app.loginAsync(credentials, result -> {
            if (result.isSuccess()) {
                Log.v("QUICKSTART", "Successfully authenticated anonymously.");
                User user = app.currentUser();
                mongoClient = user.getMongoClient("mongodb-atlas");
                mongoDatabase = mongoClient.getDatabase("arsdk");
                mongoCollection = mongoDatabase.getCollection("sdks");

                Document queryFilter  = new Document("index", "z");
                RealmResultTask<MongoCursor<Document>> findTask = mongoCollection.find(queryFilter).iterator();
                findTask.getAsync(task -> {
                    if (task.isSuccess()) {
                        MongoCursor<Document> results = task.get();
                        while (results.hasNext()) {
                            latitude.add((Double) results.next().get("latitude"));
                        }

                    } else {
                        Log.e("EXAMPLE", "failed to find documents with: ", task.getError());
                    }
                });

                findTask.getAsync(task -> {
                    if (task.isSuccess()) {
                        MongoCursor<Document> results = task.get();
                        while (results.hasNext()) {
                            longitude.add((Double) results.next().get("longitude"));
                        }
                    } else {
                        Log.e("EXAMPLE", "failed to find documents with: ", task.getError());
                    }
                });

                findTask.getAsync(task -> {
                    if (task.isSuccess()) {
                        MongoCursor<Document> results = task.get();
                        while (results.hasNext()) {
                            radius.add((Integer) results.next().get("radius"));
                        }
                    } else {
                        Log.e("EXAMPLE", "failed to find documents with: ", task.getError());
                    }
                });

                // interact with realm using your user object here
            } else {
                Log.e("QUICKSTART", "Failed to log in. Error: " + result.getError());
            }
        });

        //Кнопка играть
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                intent.putExtra("radius", radius);
                startActivity(intent);
            }
        });
    }
}