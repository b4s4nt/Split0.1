package com.example.andromeda_pc.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    public EditText FName;
    public EditText LName;
    public EditText Address;
    public EditText Email;
    public EditText Password;
    public EditText Cpassword;
    private Button Submit_btn;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        setupUI();
        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("You are Here");
               Toast.makeText( Register.this, "Registering... ", Toast.LENGTH_SHORT).show();


                    register();
            }
        });

    }
    private void setupUI() {
        FName = (EditText) findViewById(R.id.fname);
        LName =  (EditText) findViewById(R.id.lname);
        Address= (EditText)findViewById(R.id.address);
        Email = (EditText) findViewById(R.id.emailid);
        Password = (EditText) findViewById(R.id.password);
        Cpassword = (EditText) findViewById(R.id.cpassword);
        Submit_btn = (Button) findViewById(R.id.submitbtn);
    }

    private void register() {

        Boolean result = false;
        myDB = new DatabaseHelper(this);

        String f_name = FName.getText().toString();
        String l_name = LName.getText().toString();
        String add = Address.getText().toString();
        String email_id = Email.getText().toString();
        String pass = Password.getText().toString();
        String cpass = Cpassword.getText().toString();
     // Toast.makeText(Register.this,f_name ,Toast.LENGTH_SHORT).show();



        ArrayList<String> list= new ArrayList();
        list.add(f_name);
        list.add(l_name);
        list.add(add);
        list.add(email_id);
        list.add(pass);

        Toast.makeText(Register.this, list.get(4), Toast.LENGTH_SHORT).show();





        if (f_name.isEmpty() || pass.isEmpty() || email_id.isEmpty()) {
            Toast.makeText(Register.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {

            //Toast.makeText( Register.this, "Here You Are register ", Toast.LENGTH_SHORT).show();

             if (myDB.insertIntoTable(list,DatabaseHelper.Table_Name)==true){
                  Toast.makeText(Register.this, " New User Is Registered", Toast.LENGTH_SHORT).show();
                  Intent intent=  new Intent(Register.this,LoginActivity.class);
                  startActivity(intent);


              }else{
                Toast.makeText(Register.this, " New User could not be Registered", Toast.LENGTH_SHORT).show();
            }

        }

    }
}
