package com.example.bhisma.inventorysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText jetUser, jetPass;
    Button  jbtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        jetUser     = (EditText) findViewById(R.id.etUser);
        jetPass     = (EditText) findViewById(R.id.etPass);
        jbtnLogin   = (Button) findViewById(R.id.btnLogin);

        jbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sUser = jetUser.getText().toString();
                String sPass = jetPass.getText().toString();
                if((sUser.equalsIgnoreCase("bhismadw@gmail.com")) &&
                        (sPass.equalsIgnoreCase("bisbis"))){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,sUser+sPass, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
