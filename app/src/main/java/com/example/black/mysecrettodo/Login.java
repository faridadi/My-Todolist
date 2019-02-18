package com.example.black.mysecrettodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper db;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        password = (EditText) findViewById(R.id.passEditText);
        login = (Button) findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(password.getText().toString());
            }
        });
    }

    private void validate(String pass){
        String msg;
        //mengecek apakah saudah ada user atau belum jika belum maka akan ditambah user dengan default password admin
        db.cekUser();

        //mengecek password di database
        Boolean cek = db.login(password.getText().toString());
        if (cek){
            Toast.makeText(this, "Anda berhasil Login", Toast.LENGTH_SHORT).show();
            //jika password cocok maka akan pindah activity
            Intent intent = new Intent(Login.this, ChangePassword.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Password yang anda masukkan salah", Toast.LENGTH_SHORT).show();
            password.getText().clear();
            password.requestFocus();
        }
    }
}
