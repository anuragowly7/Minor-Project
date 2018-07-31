package com.example.dellpc.bloodbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Homescreen extends AppCompatActivity {
    private Button login,register,admin,send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_homescreen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        login = (Button)findViewById(R.id.button);
        send = (Button)findViewById(R.id.button23);
        register = (Button)findViewById(R.id.button2);
        admin=(Button)findViewById(R.id.button19);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Homescreen.this,AdminLogin.class);
                startActivity(i);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","anurag.tamrakar@apexcollege.edu.np",null));
                mail.putExtra(Intent.EXTRA_SUBJECT,"Chat_data for blood bank regarding Accepting/Donating Blood");
                startActivity(mail);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homescreen.this,Loign.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Homescreen.this, facts.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void buttonClick(View view){
        Intent mintent = new Intent(Homescreen.this, emergency.class);
        startActivity(mintent);
    }

    public void aboutClick(View view){
        Intent mintent = new Intent(Homescreen.this, aboutus.class);
        startActivity(mintent);
    }
}
