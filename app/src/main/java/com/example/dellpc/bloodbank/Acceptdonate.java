package com.example.dellpc.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Acceptdonate extends AppCompatActivity {
    Button accept,donate,logout,remove,ccc,profile,delete;
    DatabaseHandler db;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    TextView wel;
    Switch sw;
    String email;
    ProgressDialog progressDialog,progressDialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_acceptdonate);
        progressDialog = new ProgressDialog(Acceptdonate.this);
        progressDialog1 = new ProgressDialog(Acceptdonate.this);
        progressDialog1.setMessage("You are being removed from the donor's list...");
        accept=(Button)findViewById(R.id.button6);
        sw=(Switch)findViewById(R.id.switch4);
        ccc=(Button)findViewById(R.id.buttonchat);
        logout=(Button)findViewById(R.id.logout);
        wel=(TextView)findViewById(R.id.textView15);
        profile=(Button)findViewById(R.id.button27);
        delete=(Button)findViewById(R.id.button29);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Acceptdonate.this,chat.class);
                startActivity(i); //initialise ethe activity of blood donation
            }
        });

        final String log=getIntent().getStringExtra("log");
        firebaseAuth=FirebaseAuth.getInstance();
        final FirebaseUser us = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Registerdata");
        email = user.getEmail();

        final String nname = "";
        FirebaseDatabase.getInstance().getReference("Registerdata").orderByChild("email").equalTo(email)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            String name=snapshot.child("name").getValue(String.class);
                            wel.setText("Welcome, "+name);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
/*        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("You are being added in the donor's list...");
                progressDialog.show();
                donate.setVisibility(View.INVISIBLE);
                FirebaseDatabase.getInstance().getReference("Registerdata").orderByChild("email").equalTo(email).
                        addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                    String key =snapshot.getRef().getKey();
                                    DatabaseReference don = FirebaseDatabase.getInstance().getReference("Registerdata");
                                    don.child(key).child("donor").setValue("1");
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                Intent i = new Intent(Acceptdonate.this,Donor.class);
                startActivity(i);
                progressDialog.hide();
            }
        });*/
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Acceptdonate.this,bloodselection.class);
                startActivity(i);
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    progressDialog.setMessage("You are being added in the donor's list...");
                    progressDialog.show();
                    FirebaseDatabase.getInstance().getReference("Registerdata").orderByChild("email").equalTo(email).
                            addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                        String key =snapshot.getRef().getKey();
                                        DatabaseReference don = FirebaseDatabase.getInstance().getReference("Registerdata");
                                        don.child(key).child("donor").setValue("1");
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                    Toast.makeText(getApplicationContext(),"Your name has been added to donor's list,Thank You!",Toast.LENGTH_LONG).show();
                    progressDialog.hide();

                }
                else {

                    progressDialog1.show();
                    FirebaseDatabase.getInstance().getReference("Registerdata").orderByChild("email").equalTo(email).
                            addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                        String key =snapshot.getRef().getKey();
                                        DatabaseReference don = FirebaseDatabase.getInstance().getReference("Registerdata");
                                        don.child(key).child("donor").setValue("0");
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                    progressDialog1.hide();
                    Toast.makeText(getApplicationContext(),"Removed from donor's list",Toast.LENGTH_LONG).show();

                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Loading Profile...");
                progressDialog.show();
                Intent i = new Intent(Acceptdonate.this,userprofile.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Acceptdonate.this,Homescreen.class);
                startActivity(i);
            }
        });
/*        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog1.show();
                donate.setVisibility(View.VISIBLE);
                FirebaseDatabase.getInstance().getReference("Registerdata").orderByChild("email").equalTo(email).
                        addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                    String key =snapshot.getRef().getKey();
                                    DatabaseReference don = FirebaseDatabase.getInstance().getReference("Registerdata");
                                    don.child(key).child("donor").setValue("0");
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                progressDialog1.hide();
                Toast.makeText(getApplicationContext(),"Removed from donor's list",Toast.LENGTH_LONG).show();
            }
        });*/

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           FirebaseDatabase.getInstance().getReference("Registerdata").orderByChild("email").equalTo(email).
            addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        String key =snapshot.getRef().getKey();
                        DatabaseReference don = FirebaseDatabase.getInstance().getReference("Registerdata");
                        don.child(key).removeValue();
                        us.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            String TAG="";
                                            Log.d(TAG, "User account deleted.");
                                        }
                                    }
                                });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

                progressDialog1.hide();
                Toast.makeText(getApplicationContext(),"User Account Removed ",Toast.LENGTH_LONG).show();
                Intent i = new Intent(Acceptdonate.this,Homescreen.class);
                startActivity(i);
        }
    });

}

}


