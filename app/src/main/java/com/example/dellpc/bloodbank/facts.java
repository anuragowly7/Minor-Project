package com.example.dellpc.bloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class facts extends AppCompatActivity {
    private FirebaseRemoteConfig fbRemoteConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        fbRemoteConfig = FirebaseRemoteConfig.getInstance();
        fbRemoteConfig.setDefaults(R.xml.remote_config_params);
        fbRemoteConfig.activateFetched();
        applyConfig();
        fbRemoteConfig.fetch(0);
    }


    protected void applyConfig() {

        TextView textView = (TextView) findViewById(R.id.welcome_text);
        textView.setText(fbRemoteConfig.getString("welcome_text"));
    }

}
