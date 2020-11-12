package com.example.traffic_control;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowDetails extends AppCompatActivity {
private EditText m;
private Button b;
TextView te;
    private FirebaseDatabase database;
    private DatabaseReference namePlateRef,ownerRef,dobRef;
    private String uID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        m = (EditText) findViewById(R.id.number);
        b = (Button) findViewById(R.id.btn);
        te = (TextView) findViewById(R.id.text1);
        database = FirebaseDatabase.getInstance();
        uID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        namePlateRef = database.getReference().child(uID).child("name-plate");
        ownerRef = database.getReference().child(uID).child("owner");
        dobRef = database.getReference().child(uID).child("dob");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namePlateRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            //  String key=dataSnapshot.getKey();
                            String value;
                            value = snap.getValue(String.class);
                            if (value.equals(m.getText().toString().trim())) {
                                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();

                            /*    ownerRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot s: snap.getChildren()){
                                            te.setText(s.getValue(String.class));

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        System.out.println("The read failed: " + error.getCode());
                                    }
                                });

                                dobRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot s: snap.getChildren()){
                                            te.append(s.getValue(String.class));

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        System.out.println("The read failed: " + error.getCode());
                                    }
                                });*/


                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("The read failed: " + error.getCode());
                    }
                });


            }

        });
    }
}