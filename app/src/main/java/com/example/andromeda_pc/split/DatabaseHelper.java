package com.example.andromeda_pc.split;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

    /* to create new table :
    public static final String Table Variable name = "Table name that Appears";
    public static final String cloumn name = " Column name to display ";
    */

    public static final String Database_Name =  "SplitAmount.db";

    // For Registration Table Declaration
    public static final String Table_Name = "register_table";
    //public static final String GroupTable = "group_table";
    public static final String ID = "ID";
    public static final String first_name = "FirstName";
    public static final String last_name = "LastName";
    public static final String address1 = "Address";
    public static final String email1 = "Email";
    public static final String passw = "Password";

    //For Member Table Declaration

    public static final String MemberTable = "MemberTable";
    public static final String Member_ID= "Member_ID";
    public static final String member_name = "MemberName";
    public static final String group_name = "GroupName";
    public static final String member_email = "MemberEmail";
    public static final String amount_owed = "Amount_Owed";
    public static final String Comments = "Comments";





    public DatabaseHelper(Context context) {
        super((Context) context, Database_Name, null, 1);
       // SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {






        /*
        Create a string query
        string query 1 = + Table Variable name+" ("+
        column" name + "data type) ';

        include
        try {
          db.execSQL(query);
          System.out.println("Table Created");


      }catch (Exception e){
          System.out.println("Could Not Create Table ");
      }
         */

        // Table query for Registration
        String  query= "create table if not exists " +Table_Name+" ("+
                ID + " Integer primary key Autoincrement , " +
                first_name + " text," +
                last_name + " text," +
                address1 + " text," +
                email1 + " text," +
                passw+" text)";

        try {
            db.execSQL(query);
            System.out.println("Table Created");


        }catch (Exception e){
            System.out.println("Could Not Create Table ");
        }

        //Table Query for adding Member Table
        String  query1=  "create table if not exists " + MemberTable+ "  (" +
                Member_ID + " Integer primary key Autoincrement , " +
                member_name + " text," +
                group_name + " text," +
                member_email + " text," +
                amount_owed + " Double," +
                Comments + " text)";
        System.out.println(query1);

        try {
            db.execSQL(query1);
            System.out.println("MemberTable Created!");


        }catch (Exception e){
            System.out.println("Member table  could not be created! ");
        }


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + MemberTable);

        /*
        add for each query
        db.execSQL("DROP TABLE IF EXISTS " + Table Variable name);
         */
        onCreate(db);
       // Toast.makeText( Context: DatabaseHelper.this, "Here You Are register ", Toast.LENGTH_SHORT).show();


    }

    //Create method for putting value in each data
    /*
    public Boolean method name(ArrayList<String> list,String tableName)
     */

    public Boolean insertIntoTable(ArrayList<String> list,String tableName ){


        System.out.println("This is Table"+tableName);

        System.out.print(list.get(3).toString());
        System.out.println("Here is inside insert  new user");
        Boolean check =false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.first_name, list.get(0).toString());
        contentValues.put(this.last_name, list.get(1).toString());
        contentValues.put(this.address1, list.get(2).toString());
        contentValues.put(this.email1, list.get(3).toString());
        contentValues.put(this.passw, list.get(4).toString());
        try {
            check=db.insert( tableName,            null, contentValues)!=-1;
        }catch (Exception e){

            System.out.println("Could not Insert Data ");
            System.out.print(e.toString());
        }


         return check;





    }



    public String  getMatchingValue( String Table,  String Email) {

        System.out.println(Email);
        System.out.print(Table);
        String returnValue = "";
        Cursor rs = null;

        SQLiteDatabase db = this.getReadableDatabase();
        try{
            rs = db.rawQuery("select * from " + Table + " where " + email1 + " = '" + Email + "'", null);

        }catch (Exception e){

            System.out.println("Error in query Execution get Password");
            System.out.println(e.toString());

        }


        if (rs != null && rs.getCount() > 0) {
            rs.moveToFirst();
            returnValue = rs.getString(rs.getColumnIndex(DatabaseHelper.passw));
            rs.close();
        }
//
//
//
        return returnValue;
    }



    public  ArrayList<String> getAmount(String tableName){
        ArrayList list= new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rs = null;
        try{

           // rs = db.rawQuery("select * from " + tableName + " where " + email1 + " = '" + Email + "'", null);

             rs = db.rawQuery("select * from " + tableName , null);

        }catch (Exception e){

            System.out.println("Error in query Execution get Password");
            System.out.println(e.toString());

        }

        for (rs.moveToFirst();rs.isAfterLast();rs.moveToFirst()){
           list.add(rs.getString(4));




        }



        return list
                ;
    }

    public Boolean AddMemberDetails(ArrayList<String> list,String tableName  ){




        //System.out.print(list.get(6).toString());
        System.out.println("Here is inside add member data");
        Boolean check =false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.member_name, list.get(0).toString());
        contentValues.put(this.group_name, list.get(1).toString());
        contentValues.put(this.member_email, list.get(2).toString());
        contentValues.put(this.amount_owed, list.get(3).toString());
        contentValues.put(this.Comments, list.get(4).toString());



        try {
            check=db.insert( tableName, null, contentValues)!=-1;
            System.out.println("checked ");
        }catch (Exception e){

            System.out.println("Could not Insert  Member Data ");
            System.out.print(e.toString());
        }


        return check;





    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + MemberTable, null );
        System.out.println(res);
        return res;
    }

//

}

