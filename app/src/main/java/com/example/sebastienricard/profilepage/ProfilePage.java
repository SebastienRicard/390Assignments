package com.example.sebastienricard.profilepage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {

    protected EditText NameEditText;
    protected TextView BMIText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        NameEditText = (EditText) findViewById(R.id.NameEditText);
        BMIText = (TextView) findViewById(R.id.BMIView);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_edit:
                BMIText.setVisibility(View.GONE);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
