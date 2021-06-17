package com.bot.ksrtctrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("MSG", "signInAnonymously:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MSG", "signInAnonymously:failure", task.getException());
                            Toast.makeText(Splash.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Handler hd=new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(Splash.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },2000);
    }
}