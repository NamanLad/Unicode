package com.example.unicodeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity
{


    TextView tv_yourName, tv_yourId, tv_yourAge, tv_yourBranch ,tv_yourGender;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        tv_yourName = findViewById(R.id.tv_yourName);
        tv_yourId = findViewById(R.id.tv_yourId);
        tv_yourAge = findViewById(R.id.tv_yourAge);
        tv_yourBranch = findViewById(R.id.tv_yourBranch);
        tv_yourGender = findViewById(R.id.tv_yourGender);

        loadData();


    }

    public void loadData()
    {
        Intent intent = getIntent();

        Fields obj1 = new Fields();
        obj1.name = intent.getStringExtra("name");
        obj1.surname = intent.getStringExtra("surname");
        obj1.id = intent.getIntExtra("id", 0);
        obj1.age = intent.getIntExtra("age", 0);
        obj1.branch = intent.getStringExtra("branch");
        obj1.gender = intent.getStringExtra("gender");

        if(obj1.surname.equals(""))
            tv_yourName.setText("Welcome " + obj1.name + "!");
        else
            tv_yourName.setText("Welcome " + obj1.name + " " + obj1.surname + "!");
        tv_yourId.setText("Your id is: " + obj1.id);
        tv_yourAge.setText("Your age is: " + obj1.age);
        tv_yourBranch.setText("Your branch is: " + obj1.branch);
        tv_yourGender.setText("Your gender is: " + obj1.gender);

        System.out.println("Name is (from object)" + obj1.name);
        System.out.println("Name is (from intent)" + intent.getStringExtra("name"));
        System.out.println("Surname is " + obj1.surname);
        System.out.println("Id is " + tv_yourId.getText().toString());
        System.out.println("Age is " + tv_yourAge.getText().toString());
        System.out.println("Branch is " + tv_yourBranch.getText().toString());
        System.out.println("Gender is " + tv_yourGender.getText().toString());
    }
}
