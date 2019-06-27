package com.example.andromeda_pc.split;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class welcome extends AppCompatActivity {
//    private Button btcreate_grp;
    private Button Calculate;
    private EditText TotalAmount;
    private EditText MemberCount;
    private Button add_Details;
    private Double sum;

//    private Button profile;
    private EditText AmountPp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        AmountPp= (EditText) findViewById(R.id.amountperPerson);
        TotalAmount =(EditText)findViewById(R.id.BillAmount);
        MemberCount= (EditText)findViewById(R.id.memberCount);
        add_Details= (Button)findViewById(R.id.bt_AddDetail);
        add_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(welcome.this, AddDetails.class);
                startActivity(intent);

            }
        });







//
//
//
//






          Calculate= (Button)findViewById(R.id.bt_calculate);
          Calculate.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  Double x;
                  Double y;

                  x= Double.parseDouble(TotalAmount.getText().toString());
                  y= Double.parseDouble(MemberCount.getText().toString());
                  sum= x/y;

                  AmountPp.setText(Double.toString(sum));
//);

              }
          });






//
    }



}
