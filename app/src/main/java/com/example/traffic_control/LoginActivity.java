package com.example.traffic_control;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private EditText mOwner, mPlate, mDob;
    private Button mBtn,btn;
    private String uID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPlate = (EditText) findViewById(R.id.t1);
        mOwner = (EditText) findViewById(R.id.t2);
        mDob = (EditText) findViewById(R.id.t3);
        mBtn = (Button) findViewById(R.id.btn);
        btn =(Button)findViewById(R.id.btn1);


        uID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference().child(uID);
        DatabaseReference namePlateRef = mRootRef.child("name-plate");
        DatabaseReference ownerRef = mRootRef.child("owner");
        DatabaseReference dobRef = mRootRef.child("dob");

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                namePlateRef.push().setValue(mPlate.getText().toString().trim());

                mPlate.setText("");
                ownerRef.push().setValue(mOwner.getText().toString().trim());

                mOwner.setText("");
                dobRef.push().setValue(mDob.getText().toString().trim());

                mDob.setText("");
                Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ShowDetails.class));
            }
        });



    }
}

