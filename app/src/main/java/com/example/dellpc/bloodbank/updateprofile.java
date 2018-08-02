package com.example.dellpc.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateprofile extends AppCompatActivity {
    Button back,logout,update;
    TextView a,b,c,d,e,f;
    ProgressDialog progressDialog;
    String email;
    FirebaseAuth firebaseAuth;
    public String sex="",bloodgrp="",phone="",birth="",useri="",pass="",nam="",add="";
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_updateprofile);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String ck = "";
        if (user != null) {
            ck = user.getEmail();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Registerdata");
        progressDialog = new ProgressDialog(updateprofile.this);
        back=(Button)findViewById(R.id.button11);
        update=(Button)findViewById(R.id.button30);
        logout=(Button)findViewById(R.id.button14);
        a=(TextView)findViewById(R.id.textView16);
        b=(TextView)findViewById(R.id.textView22);
        c=(TextView)findViewById(R.id.textView23);
        d=(TextView)findViewById(R.id.textView24);
        e=(TextView)findViewById(R.id.textView26);
        f=(TextView)findViewById(R.id.textView54);
        progressDialog.setMessage("Fetching user's info...");
        FirebaseDatabase.getInstance().getReference("Registerdata").orderByChild("email").equalTo(ck).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String blood = snapshot.child("bloodgrp").getValue(String.class);
                    String dob = snapshot.child("dob").getValue(String.class);
                    String sex = snapshot.child("sex").getValue(String.class);
                    String phone = snapshot.child("number").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);
                    a.setText(name);
                    b.setText(dob);
                    c.setText(phone);
                    d.setText(sex);
                    e.setText(blood);
                    f.setText(address);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        progressDialog.hide();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(updateprofile.this,userprofile.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(updateprofile.this,Homescreen.class);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                databaseReference = FirebaseDatabase.getInstance().getReference("Registerdata");
                email = user.getEmail();
                FirebaseDatabase.getInstance().getReference("Registerdata").orderByChild("email").equalTo(email).
                        addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                    String key =snapshot.getRef().getKey();
                                    DatabaseReference don = FirebaseDatabase.getInstance().getReference("Registerdata");
                                    don.child(key).child("name").setValue(a.getText().toString());
                                    don.child(key).child("dob").setValue(b.getText().toString());
                                    don.child(key).child("number").setValue(c.getText().toString());
                                    don.child(key).child("sex").setValue(d.getText().toString());
                                    don.child(key).child("bloodgrp").setValue(e.getText().toString());
                                    don.child(key).child("address").setValue(f.getText().toString());
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                Toast.makeText(getApplicationContext(),"Your Profile has been updated",Toast.LENGTH_LONG).show();
                progressDialog.hide();

            }

        });



    }
}
