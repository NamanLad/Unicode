package com.example.unicodeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    String name, surname, branch, gender;
    int id, age;
    boolean signInSuccessful = false;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String ID = "id";
    public static final String AGE = "age";
    public static final String BRANCH = "branch";
    public static final String GENDER = "nullGender";

    EditText et_name, et_surname, et_id, et_age;

    RadioGroup rg;
    RadioButton rb_male, rb_female, rb_final;

    Button btn_signIn;

    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        if(sharedPreferences.getBoolean("myBoolean", true))
        {
            Intent intent = new Intent(MainActivity.this, WelcomePage.class);
            intent.putExtra("name", sharedPreferences.getString(NAME, ""));
            intent.putExtra("surname", sharedPreferences.getString(SURNAME, ""));
            intent.putExtra("id", sharedPreferences.getInt(ID, 0));
            intent.putExtra("age", sharedPreferences.getInt(AGE, 0));
            intent.putExtra("branch", sharedPreferences.getString(BRANCH, ""));
            intent.putExtra("gender", sharedPreferences.getString(GENDER, ""));
            startActivity(intent);
        }



        et_name = findViewById(R.id.et_name);
        et_surname = findViewById(R.id.et_surname);
        et_id = findViewById(R.id.et_id);
        et_age = findViewById(R.id.et_age);

        rg = findViewById(R.id.rg);

        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);

        btn_signIn = findViewById(R.id.btn_signIn);


        spinner = findViewById(R.id.spinner);
        List<String> list = new ArrayList<>();
        //        list.add("");
        list.add("Computer Engineering");
        list.add("Information Technology");
        list.add("Mechanical");
        list.add("Electronics");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                branch = parent.getItemAtPosition(position).toString();
                //                Toast.makeText(MainActivity.this, "Selected : " + branch, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {}
        });


        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                if(et_name.getText().toString().equals("") || et_id.getText().toString().equals("") || et_age.getText().toString().equals("") || (!rb_male.isChecked() && !rb_female.isChecked()))
                    Toast.makeText(MainActivity.this, "Please enter all the inputs", Toast.LENGTH_LONG).show();
                else
                {
                    name = et_name.getText().toString();
                    surname = et_surname.getText().toString();
                    id = Integer.parseInt(et_id.getText().toString());
                    age = Integer.parseInt(et_age.getText().toString());

                    if (rb_female.isChecked())
                        rb_final = rb_female;
                    else
                        rb_final = rb_male;

                    gender = rb_final.getText().toString();
                    signInSuccessful = true;

                    Fields obj1 = new Fields();
                    obj1.name = name;
                    obj1.surname = surname;
                    obj1.age = age;
                    obj1.id = id;
                    obj1.branch = branch;
                    obj1.gender = rb_final.getText().toString();

                    saveAndSendData(obj1);

                }

                //                    editor.putBoolean("signInSuccessful", signInSuccessful);
                //                    editor.commit();
                //
                //                    Intent intent = new Intent(MainActivity.this, WelcomePage.class);
                //                    intent.putExtra("name", name);
                //                    intent.putExtra("surname", surname);
                //                    intent.putExtra("id", id);
                //                    intent.putExtra("age", age);
                //                    intent.putExtra("branch", branch);
                //                    intent.putExtra("gender", gender);
                //                    startActivity(intent);

            }
        });

    }





    void saveAndSendData(Fields obj1)
    {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(NAME, obj1.name);
        editor.putString(SURNAME, obj1.surname);
        editor.putInt(ID, obj1.id);
        editor.putInt(AGE, obj1.age);
        editor.putString(BRANCH, obj1.branch);
        editor.putString(GENDER, obj1.gender);
        editor.putBoolean("myBoolean", signInSuccessful);

        editor.apply();

        Intent intent = new Intent(MainActivity.this, WelcomePage.class);
        intent.putExtra("name", obj1.name);
        intent.putExtra("surname", obj1.surname);
        intent.putExtra("id", obj1.id);
        intent.putExtra("age", obj1.age);
        intent.putExtra("branch", obj1.branch);
        intent.putExtra("gender", obj1.gender);
        startActivity(intent);
    }

}
