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

import org.w3c.dom.Text;

public class ProfilePage extends AppCompatActivity {

    public EditText NameEditText;
    protected TextView BMIText;
    protected TextView BMINumber;
    protected TextView BMRText;
    protected TextView BMRNumber;
    public EditText Age;
    public EditText Weight;
    public EditText Height;
    Button SaveButton;

    boolean verified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        NameEditText = (EditText) findViewById(R.id.NameEditText);
        BMIText = (TextView) findViewById(R.id.BMIText);
        BMINumber = (TextView) findViewById(R.id.BMINumber);
        BMRText = (TextView) findViewById(R.id.BMRText);
        BMRNumber = (TextView) findViewById(R.id.BMRNumber);
        Age = (EditText) findViewById(R.id.AgeNumber);
        Weight = (EditText) findViewById(R.id.WeightNumber);
        Height = (EditText) findViewById(R.id.HeightNumber);


        SaveButton = (Button) findViewById(R.id.SaveButton);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ProfileName", NameEditText.getText().toString());
                editor.commit();

                BMIText.setVisibility(View.VISIBLE);
                BMINumber.setVisibility(View.VISIBLE);
                BMRText.setVisibility(View.VISIBLE);
                BMRNumber.setVisibility(View.VISIBLE);
                SaveButton.setVisibility(View.GONE);
            }
        });
    }

    protected void TextVerifier(){
        if (NameEditText == null)
            verified = false;
        else
            verified = true;
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
                BMINumber.setVisibility(View.GONE);
                BMRText.setVisibility(View.GONE);
                BMRNumber.setVisibility(View.GONE);
                SaveButton.setVisibility(View.VISIBLE);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
