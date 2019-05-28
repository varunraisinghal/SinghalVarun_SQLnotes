package com.example.mycontactapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    DatabaseHelper myDb;
    EditText editName, editPhone, editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editPhone = findViewById(R.id.editText_phone);
        editAddress = findViewById(R.id.editText_address);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp","DatabaseHelper: instantiated the DatabaseHelper");

    }
    public void addData(View view)
    {
        boolean isInserted = myDb.insertData(editName.getText().toString(), Integer.parseInt(editPhone.getText().toString()), editAddress.getText().toString());

        if(isInserted == true)
        {
            Toast.makeText(MainActivity.this,"Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Failed - contact not insrted", Toast.LENGTH_LONG).show();
        }
    }

}