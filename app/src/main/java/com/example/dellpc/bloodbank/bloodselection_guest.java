package com.example.dellpc.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class bloodselection_guest extends AppCompatActivity {
    ProgressDialog progressDialog;
    Button submit,logout;
    String bloodgrp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bloodselection_guest);
        progressDialog = new ProgressDialog(bloodselection_guest.this);
        Spinner myspinner=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(bloodselection_guest.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myAdapter);
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String label = adapterView.getItemAtPosition(i).toString();
                if (label.equals("Select any"))
                    bloodgrp = "";
                else
                    bloodgrp = label;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        submit=(Button)findViewById(R.id.button9);
        logout=(Button)findViewById(R.id.button13);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bloodgrp.equals("")){
                    Toast.makeText(getApplicationContext(), "Select Any", Toast.LENGTH_LONG).show();
                }
                else {
                    progressDialog.setMessage("Fetching donor;s list...");
                    progressDialog.show();
                    Intent i = new Intent(bloodselection_guest.this, ViewListContents_Guest.class);
                    i.putExtra("bloodgrp",bloodgrp);
                    startActivity(i);
                    progressDialog.hide();
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Signing out...");
                progressDialog.show();
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(bloodselection_guest.this,Homescreen.class);
                startActivity(i);
                progressDialog.hide();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(bloodselection_guest.this,Admin.class);
        startActivity(i);
    }
}



