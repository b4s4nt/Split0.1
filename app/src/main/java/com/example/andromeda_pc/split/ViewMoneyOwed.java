package com.example.andromeda_pc.split;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewMoneyOwed extends AppCompatActivity {

//    private  EditText MemberName= (EditText)findViewById(R.id.etMemName);
//    private  EditText GroupName= (EditText )findViewById(R.id.etGrpName);
//    private  EditText MemEmail= (EditText )findViewById(R.id.etMemEmail);
    private TextView AmountOwed;
//    private  EditText Comment= (EditText )findViewById(R.id.etComments);







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_view_money_owed);
        AmountOwed= (TextView ) findViewById(R.id.ao);

        ArrayList<String> list= new ArrayList<>();
        DatabaseHelper db= new DatabaseHelper(ViewMoneyOwed.this);
        list= db.getAmount(DatabaseHelper.MemberTable);


//        MemberName.setText(list.get(0));
//        GroupName.setText(list.get(1));
//        MemEmail.setText(list.get(2));
//        AmountOwed.setText(list.get(3).toString());
//        Comment.setText(list.get(4));
            double sum=0;
          for(String x: list){
              sum= sum+Double.parseDouble(x);
          }
        AmountOwed.setText(Double.toString(sum));

    }
}
