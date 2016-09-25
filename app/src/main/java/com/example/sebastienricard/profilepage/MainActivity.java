package com.example.sebastienricard.profilepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProfileButton = (Button) findViewById(R.id.ProfileButton);
    }

    protected void onStart(){
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("ProfileName", null);
        if(name == null)
            goToProfileActivity();
        else
            ProfileButton.setText(name);

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilePage.class);
                startActivity(intent);
            }
        });

    }

    void goToProfileActivity(){
        Intent intent = new Intent(MainActivity.this, ProfilePage.class);
        startActivity(intent);
    }


}