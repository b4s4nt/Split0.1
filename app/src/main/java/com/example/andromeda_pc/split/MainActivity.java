package com.example.andromeda_pc.split;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btlogin;
    private Button btregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btlogin = (Button)findViewById(R.id.bt_login);
        btlogin.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    openLoginActivity();


                }
            });
        btregister = (Button)findViewById(R.id.bt_register);
        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();

            }
        });



        }
    public void openLoginActivity() {
        Intent intent =new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void openRegisterActivity() {
        Intent intent =new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }


    }


