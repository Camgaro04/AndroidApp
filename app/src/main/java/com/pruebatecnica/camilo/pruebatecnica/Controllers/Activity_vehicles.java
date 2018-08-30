package com.pruebatecnica.camilo.pruebatecnica.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.pruebatecnica.camilo.pruebatecnica.Adapter.Adapter_Vehicles;
import com.pruebatecnica.camilo.pruebatecnica.DataBase.DataBaseContract;
import com.pruebatecnica.camilo.pruebatecnica.DataBase.SqlDbHelper;
import com.pruebatecnica.camilo.pruebatecnica.Models.Vehicle;
import com.pruebatecnica.camilo.pruebatecnica.R;

import java.util.ArrayList;


public class Activity_vehicles extends AppCompatActivity {

    public FloatingActionButton fabLaunchInsertVehicles;
    public Button btnOpenInsert;
    public ListView listVehicles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        listVehicles = findViewById(R.id.listVehicles);

        btnOpenInsert = findViewById(R.id.btnOpenInsert);
        btnOpenInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertVehicle();
            }
        });

        GetVehicles();
    }

    private void GetVehicles(){
        SqlDbHelper sqlDbHelper = new SqlDbHelper(this);
        ArrayList<Vehicle> dataVehicles = sqlDbHelper.GetAllVehicles();
        Adapter_Vehicles adapter_vehicles = new Adapter_Vehicles(this,dataVehicles);
        listVehicles.setAdapter(adapter_vehicles);

    }

    private void InsertVehicle(){
        Intent launchInsertVehicle = new Intent( this, Activity_insert_vehicle.class);
        startActivity(launchInsertVehicle);
    }
}
