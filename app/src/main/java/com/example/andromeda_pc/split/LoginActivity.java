package com.example.andromeda_pc.split;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button Login;
    //EditText UserName;
    EditText Email;
    EditText Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setViewUI();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 DatabaseHelper db= new DatabaseHelper(LoginActivity.this);


               String passfromDb = db.getMatchingValue(DatabaseHelper.Table_Name,Email.getText().toString().trim());
               System.out.println(passfromDb);

                try {

                    if (!Password.getText().toString().isEmpty()){


                        if(passfromDb.trim().equals(Password.getText().toString().trim()) ){

                            Toast.makeText(LoginActivity.this, passfromDb.trim(),Toast.LENGTH_SHORT).show();


                            Toast.makeText(LoginActivity.this, "Login Successful!",Toast.LENGTH_SHORT).show();
                            //Toast.makeText(LoginActivity.this, Password.getText().toString(),Toast.LENGTH_SHORT).show();
//



                            Intent intent= new Intent(LoginActivity.this,welcome.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Login Failed! Please enter the correct details!",Toast.LENGTH_SHORT).show();
                        }


                    }

                }catch (Exception e){
                    Toast.makeText(LoginActivity.this," Error in getting Values",Toast.LENGTH_SHORT).show();

                }


            }
        });


    }

    private void setViewUI() {

      final DatabaseHelper db= new DatabaseHelper(this);
        Login= (Button) findViewById(R.id.login);
        Email= (EditText)findViewById(R.id.login_email);
        Password=(EditText)findViewById(R.id.login_pw);


    }

}
