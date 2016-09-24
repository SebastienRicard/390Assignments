package com.example.sebastienricard.profilepage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfilePage extends AppCompatActivity {

    protected EditText NameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        NameEditText = (EditText) findViewById(R.id.NameEditText);
        final Button SaveButton = (Button) findViewById(R.id.SaveButton);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ProfileName", NameEditText.getText().toString());
                editor.commit();
            }
        });
    }
}
