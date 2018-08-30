package com.pruebatecnica.camilo.pruebatecnica.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.pruebatecnica.camilo.pruebatecnica.Adapter.Adapter_persons;
import com.pruebatecnica.camilo.pruebatecnica.DataBase.SqlDbHelper;
import com.pruebatecnica.camilo.pruebatecnica.Models.Person;
import com.pruebatecnica.camilo.pruebatecnica.R;

import java.util.ArrayList;

public class Activity_persons extends AppCompatActivity {

    public ListView listViewPersons;
    public Button btnInsertPerson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        listViewPersons = findViewById(R.id.listPersons);
        btnInsertPerson = findViewById(R.id.insertPerson);

        btnInsertPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchInsert = new Intent(Activity_persons.this,Activity_insert_person.class);
                startActivity(launchInsert);
            }
        });

        GetPersons();
    }

    public void GetPersons (){
        try{
            SqlDbHelper db = new SqlDbHelper(this);
            ArrayList<Person> dataPersons = db.GetAllPersons();
            if(!dataPersons.isEmpty()){

                Adapter_persons adapter_vehicles = new Adapter_persons(this,dataPersons);
                listViewPersons.setAdapter(adapter_vehicles);
            }
        }catch (Exception ex){
            Log.e("error",ex.getMessage());
        }
    }
}
