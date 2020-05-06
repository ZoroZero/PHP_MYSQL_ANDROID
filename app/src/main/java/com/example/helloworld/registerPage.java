package com.example.helloworld;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.helloworld.ui.login.LoginActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerPage extends AppCompatActivity implements View.OnClickListener{
    public EditText usernameText;
    public EditText passwordText;
    public Button registerButton;
    public Button goToLoginButton;
    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        usernameText = (EditText) findViewById(R.id.usernameEditText);

        passwordText = (EditText) findViewById(R.id.passwordEditText);
        registerButton = (Button) findViewById(R.id.regButton);
        goToLoginButton = (Button) findViewById(R.id.loginPageButton);

        progressDialog = new ProgressDialog(this);

        registerButton.setOnClickListener(this);
        goToLoginButton.setOnClickListener(this);
    }

    public void registerUser(){
        final String username = usernameText.getText().toString().trim();
        final String password = passwordText.getText().toString().trim();

        progressDialog.setMessage("Processing request");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == registerButton){
            registerUser();
        }
        if(v == goToLoginButton){
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
    }
}
