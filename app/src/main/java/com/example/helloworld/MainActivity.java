package com.example.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText num1 = (EditText) findViewById(R.id.editText);
                EditText num2 = (EditText) findViewById(R.id.editText2);
                TextView res = (TextView) findViewById(R.id.ResultTextView);

                int firstNum = Integer.parseInt(num1.getText().toString());
                int secondNum = Integer.parseInt(num2.getText().toString());
                int result = firstNum + secondNum;
                res.setText(result + "");
            }
        });


        // Second activity button
        Button secondActivityButton = (Button) findViewById(R.id.secondActitvityButton);
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent1 = new Intent(getApplicationContext(), SecondActivity.class);
                // show pass information to second scene
                startIntent1.putExtra("com.example.helloworld.text", "Hello world!!!");
                //
                startActivity(startIntent1);
            }
        });


        // Google activity
        Button googleActivityButton = (Button) findViewById(R.id.googleButton);
        googleActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String google_url = "http://www.google.com";
                Uri url = Uri.parse(google_url);

                // Intent
                Intent goToGoogle = new Intent(Intent.ACTION_VIEW, url);

                if(goToGoogle.resolveActivity(getPackageManager()) != null){
                    startActivity(goToGoogle);
                }
            }
        });

        // Register
        final Button registerPage = (Button) findViewById(R.id.registerPage);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterPage = new Intent(getApplicationContext(), registerPage.class);

                startActivity(goToRegisterPage);
            }
        });
    }
}
