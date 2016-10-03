package com.example.sebastienricard.profilepage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.jar.Attributes;

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
    SeekBar seekbar;
    boolean SaveClicked;
    Toast toast;

    private KeyListener NameListener;
    private KeyListener AgeListener;
    private KeyListener WeightListener;
    private KeyListener HeightListener;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        sharedPreferences = getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE);

            NameEditText = (EditText) findViewById(R.id.NameEditText);
            BMIText = (TextView) findViewById(R.id.BMIText);
            BMINumber = (TextView) findViewById(R.id.BMINumber);
            BMRText = (TextView) findViewById(R.id.BMRText);
            BMRNumber = (TextView) findViewById(R.id.BMRNumber);
            Age = (EditText) findViewById(R.id.AgeNumber);
            Weight = (EditText) findViewById(R.id.WeightNumber);
            Height = (EditText) findViewById(R.id.HeightNumber);

        if(sharedPreferences.getString("ProfileName", null) != null){
            NameEditText.setText(sharedPreferences.getString("ProfileName", null));
            Age.setText(sharedPreferences.getString("Age", null));
            Weight.setText(sharedPreferences.getString("Weight", null));
            Height.setText(sharedPreferences.getString("Height", null));

            BMIText.setVisibility(View.VISIBLE);
            BMINumber.setVisibility(View.VISIBLE);
            BMRText.setVisibility(View.VISIBLE);
            BMRNumber.setVisibility(View.VISIBLE);
        }

        SaveClicked = false;
        toast = Toast.makeText( getApplicationContext(), null, Toast.LENGTH_LONG );

        NameListener = NameEditText.getKeyListener();
        AgeListener = Age.getKeyListener();
        WeightListener = Weight.getKeyListener();
        HeightListener = Height.getKeyListener();

        NameEditText.setKeyListener(null);
        Age.setKeyListener(null);
        Weight.setKeyListener(null);
        Height.setKeyListener(null);

//        seekbar = (SeekBar) findViewById(R.id.seekBar);

       //String sAge = Age.getText().toString();
       /* final int iAge = Integer.parseInt(Age.getText().toString());
        final double dWeight = Double.parseDouble( Weight.getText().toString());
        final double dHeight = Double.parseDouble( Height.getText().toString());*/

        SaveButton = (Button) findViewById(R.id.SaveButton);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("ProfilePreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("ProfileName", NameEditText.getText().toString());
                editor.putString("Age",  Age.getText().toString() );
                editor.putString("Weight", Weight.getText().toString() );
                editor.putString("Height", Height.getText().toString() );

                if(  NameEditText.getText().toString().isEmpty() )
                {
                    toast.makeText( getApplicationContext(), "Insert all the correct information!", Toast.LENGTH_LONG ).show();
                    return;
                }

                if( Integer.parseInt( Age.getText().toString() )  <= 0 || Integer.parseInt(Age.getText().toString() ) > 150 ||
                        !android.text.TextUtils.isDigitsOnly( Age.getText().toString() ) || Age.getText().toString().isEmpty()  )
                {
                    toast.makeText(getApplicationContext(), "Insert the correct age!", Toast.LENGTH_LONG ).show();
                    return;
                }

                if( Double.parseDouble( Weight.getText().toString() ) <= 0 || Double.parseDouble( Weight.getText().toString() ) > 1000 ||
                        !android.text.TextUtils.isDigitsOnly( Weight.getText().toString() ) || Weight.getText().toString().isEmpty() ) {
                    toast.makeText(getApplicationContext(), "Insert the correct weight!", Toast.LENGTH_LONG).show();
                    return;
                }

                if( Double.parseDouble( Height.getText().toString() ) <= 0 || Double.parseDouble( Height.getText().toString() ) > 75 ||
                        !android.text.TextUtils.isDigitsOnly( Height.getText().toString() ) || Height.getText().toString().isEmpty() ) {
                    toast.makeText(getApplicationContext(), "Insert the correct height!", Toast.LENGTH_LONG).show();
                    return;
                }

                editor.commit();

                BMIText.setVisibility(View.VISIBLE);
                BMINumber.setVisibility(View.VISIBLE);
                BMRText.setVisibility(View.VISIBLE);
                BMRNumber.setVisibility(View.VISIBLE);
                SaveButton.setVisibility(View.GONE);

                BMINumber.append( Double.toString( ( Double.parseDouble( Age.getText().toString() )*703 )/
                        Double.parseDouble(Height.getText().toString()) * Double.parseDouble( Height.getText().toString() )) );



              /*  if( sGender.contains("F") || sGender.contains("f") )*/
                    BMRNumber.append( Double.toString( 655 + 4.35* Double.parseDouble(Weight.getText().toString()) +
                            4.7*Double.parseDouble(Height.getText().toString() ) ) );
            /*    else if( sGender.contains("M") || sGender.contains("m") ){
                    BMR.append( Double.toString( 66 + 6.23* Double.parseDouble( sWeight) + 12.7*Double.parseDouble( sHeight )
                            - 6.8*Double.parseDouble( sAge ) ) );}*/

                SaveClicked = true;
                toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_LONG ).show();
            }
        });
    }

/*    protected void TextVerifier(){
        if (NameEditText == null)
            verified = false;
        else
            verified = true;
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                if (SaveClicked)
                    NavUtils.navigateUpFromSameTask(this);
                else {
                    toast.makeText(getApplicationContext(), "please!", Toast.LENGTH_LONG);
                    toast.show();
                }
                return true;

            case R.id.action_edit:
                BMIText.setVisibility(View.GONE);
                BMINumber.setVisibility(View.GONE);
                BMRText.setVisibility(View.GONE);
                BMRNumber.setVisibility(View.GONE);
                SaveButton.setVisibility(View.VISIBLE);

                NameEditText.setKeyListener(NameListener);
                Age.setKeyListener(AgeListener);
                Weight.setKeyListener(WeightListener);
                Height.setKeyListener(HeightListener);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
