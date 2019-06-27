package com.example.andromeda_pc.split;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddDetails extends AppCompatActivity {

    private EditText MemName;
    private EditText GrpName;
    private EditText MemEmail;
    private EditText AmountO;
    private EditText Commts;
    private Button Bt_AddMemData;
    private  Button bt_viewAmout;
    private Button BT_ViewAll;
    DatabaseHelper myDB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        setupUI();




        Bt_AddMemData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("You are Here");
                Toast.makeText( AddDetails.this, "Here You Are ", Toast.LENGTH_SHORT).show();
                AddMemberData();

            }
        });

        ViewAll();

        bt_viewAmout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddDetails.this, ViewMoneyOwed.class);
                Toast.makeText( AddDetails.this, "Viewing the amount you owed from others...", Toast.LENGTH_SHORT).show();
                startActivity(intent);

//                Intent intent= new Intent(AddDetails.this, ViewMoneyOwed.class);
//                Toast.makeText( AddDetails.this, "Here You Are going to View  ", Toast.LENGTH_SHORT).show();
//                startActivity(intent);


            }
        });
    }

    public void ViewAll(){
        BT_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Check 1");
                Cursor res = myDB1.getAllData();
                System.out.println("Check 2");
                if(res.getCount()==0){
                    // show message
                    System.out.println("Check 3");
                    showMessage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                System.out.println("Check 4");
                while(res.moveToNext()){
                    buffer.append("Member_ID :"+res.getString(0)+ "\n");
                    buffer.append("member_name :"+res.getString(1)+ "\n");
                    buffer.append("group_name :"+res.getString(2)+ "\n");
                    buffer.append("member_email :"+res.getString(3)+ "\n");
                    buffer.append("amount_owed :"+res.getString(4)+ "\n");
                    buffer.append("Comments :"+res.getString(5)+ "\n\n");
                    System.out.println("Check 5");

                }

                //Show all data
                showMessage("Data", buffer.toString());
                System.out.println("Check 6");



//                Intent intent= new Intent(AddDetails.this, ViewMoneyOwed.class);
//                Toast.makeText( AddDetails.this, "Viewing all the transactions..", Toast.LENGTH_SHORT).show();
//                startActivity(intent);

            }
        });
    }


    private void setupUI() {
        MemName = (EditText) findViewById(R.id.etMemName);
        GrpName = (EditText) findViewById(R.id.etGrpName);
        MemEmail = (EditText) findViewById(R.id.etMemEmail);
        AmountO = (EditText) findViewById(R.id.etAmountowed);
        Commts = (EditText) findViewById(R.id.etComments);
        Bt_AddMemData = (Button) findViewById(R.id.bt_AddData);
        bt_viewAmout=(Button) findViewById(R.id.bt_View);
        BT_ViewAll=(Button) findViewById(R.id.bt_ViewAll);
    }

    private void AddMemberData() {

        Boolean result = false;
        myDB1 = new DatabaseHelper(this);

        String Mem_Name = MemName.getText().toString();
        String Grp_Name = GrpName.getText().toString();
        String Mem_Email = MemEmail.getText().toString();
        String Amount_O = AmountO.getText().toString();
        String Comm = Commts.getText().toString();

        Toast.makeText(AddDetails.this,Mem_Name ,Toast.LENGTH_SHORT).show();


        ArrayList<String> list = new ArrayList();
        list.add(Mem_Name);
        list.add(Grp_Name);
        list.add(Mem_Email);
        list.add(Amount_O);
        list.add(Comm);

        Toast.makeText(AddDetails.this, list.get(4), Toast.LENGTH_SHORT).show();

        if (Mem_Name.isEmpty() || Grp_Name.isEmpty() || Mem_Email.isEmpty() || Amount_O.isEmpty()|| Comm.isEmpty()) {
            Toast.makeText(AddDetails.this, "Please enter all the details", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText( AddDetails.this, "Here You Are register ", Toast.LENGTH_SHORT).show();

            if (myDB1.AddMemberDetails(list, DatabaseHelper.MemberTable)){
                Toast.makeText(AddDetails.this, " Member Data has been Added", Toast.LENGTH_LONG).show();
                Intent intent=  new Intent(AddDetails.this,AddDetails.class);
                startActivity(intent);


            }

        }
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
