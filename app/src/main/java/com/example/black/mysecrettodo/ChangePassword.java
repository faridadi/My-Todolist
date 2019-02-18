package com.example.black.mysecrettodo;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    DatabaseHelper db;
    private EditText oldPass;
    private EditText newPass;
    private EditText confirmPass;
    private Button change;

    NotificationCompat.Builder mBuilder;
    PendingIntent mResultPendingIntent;
    TaskStackBuilder mTaskStackBuilder;
    Intent mResultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        db = new DatabaseHelper(this);
        oldPass = (EditText) findViewById(R.id.oldPass);
        newPass = (EditText) findViewById(R.id.newPass);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        change = (Button) findViewById(R.id.changeBtn);


        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("My Secret Todo");
        mBuilder.setContentText("Aplikasi sedang berjalan");
        mResultIntent = new Intent(this,ChangePassword.class);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(oldPass.getText().toString(), newPass.getText().toString(), confirmPass.getText().toString());
            }
        });

    }

    private void  validate(String oldPass, String newPass, String confirmPass){
        if (oldPass =="" && newPass == "" && confirmPass == ""){
            Toast.makeText(this, "Data yang anda masukkan tidak valid", Toast.LENGTH_SHORT).show();
        }else {
            if (!confirmPass.equals(newPass)) {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
            }else{
                if (db.changePassword(oldPass, newPass)) {
                    Toast.makeText(this, "Password Berhasil di ganti", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Password gagal diganti", Toast.LENGTH_SHORT).show();
                }
            }

        }
        this.oldPass.getText().clear();
        this.newPass.getText().clear();
        this.confirmPass.getText().clear();
    }
}
