package com.example.blue7;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    TextView textViewForgotPassword, textViewRegister;
    CheckBox checkBox;
    ProgressBar progressBar;
Button login;

    private sharedPreferenceConfig sharedPreferenceConfig;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean savelogin;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferenceConfig = new sharedPreferenceConfig(getApplicationContext());

        editTextUsername = findViewById(R.id.editTextLoginUsername);
  checkBox=findViewById(R.id.checkBox);
        editTextPassword = findViewById(R.id.editTextLoginPassword);
         progressBar = findViewById(R.id.progressBarSignIn);


sharedPreferences = getSharedPreferences("loginRef", MODE_PRIVATE);
editor=sharedPreferences.edit();


        login= findViewById(R.id.button3);
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

login();    }
});
   savelogin=sharedPreferences.getBoolean("savelogin", true);
if(savelogin==true){
    editTextUsername.setText(sharedPreferences.getString("username",null));
    editTextPassword.setText(sharedPreferences.getString("password",null));

    }}
    public void login() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
      progressBar.setVisibility(View.VISIBLE);

        if (username.equals(getResources().getString(R.string.user_name)) && password.equals((getResources().getString(R.string.user_password)))) {
            Toast.makeText(this , "succeess ...", Toast.LENGTH_LONG).show();

            progressBar.setVisibility(View.INVISIBLE);

            startActivity(new Intent(this, MainActivity.class));
            sharedPreferenceConfig.writeLoginStatus(true);
            finish();

            if(checkBox.isChecked()){
                editor.putBoolean("savelogin",true);
                editor.putString("username", username);
                editor.putString("password", password);
                editor.commit();

            }
        } else {
            Toast.makeText(this, "Login failed! Try again ...", Toast.LENGTH_LONG).show();
            editTextUsername.setText("");
            editTextPassword.setText("");
        }

    }






}
