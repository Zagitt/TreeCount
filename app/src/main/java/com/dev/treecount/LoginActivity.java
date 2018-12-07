package com.dev.treecount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LogApp";
    //Declaracion de variables del xml
    private EditText txtLoginCorreo;
    private EditText txtLoginPassword;
    private Button btnIngresar;
    private Button btnRegistrar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        txtLoginCorreo = (EditText) findViewById(R.id.txtLoginCorreo);
        txtLoginPassword = (EditText) findViewById(R.id.txtLoginPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
//        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarUsuario();
            }
        });

        /*
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        }); */

    }
/*
    private void RegistrarUsuario() {
        Intent act = new Intent(this, RegisterActivity.class);
        startActivity(act);
    }
*/
    //entra en el examen las validaciones 3pts
    private void ValidarUsuario() {
        if(txtLoginCorreo.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese su Correo", Toast.LENGTH_SHORT).show();
            txtLoginCorreo.requestFocus();
            return;
        }
        if(txtLoginPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese su Clave", Toast.LENGTH_SHORT).show();
            txtLoginPassword.requestFocus();
            return;
        }
        if(txtLoginPassword.getText().toString().length()<6){
            Toast.makeText(this, "El password debe ser mayor a 6 caracteres", Toast.LENGTH_SHORT).show();
            txtLoginPassword.requestFocus();
            return;
        }

        String email = txtLoginCorreo.getText().toString();
        String password = txtLoginPassword.getText().toString();

        //Firebase
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
// [END sign_in_with_email]



    }//fin validar

    private void updateUI(FirebaseUser user) {

        if(user != null){
            Intent act= new Intent(this, BrigadaActivity.class); //va hacia el main activity
            startActivity(act);
            finish();
        }
    }

    public void OnStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

}
