package com.pruebatecnica.camilo.pruebatecnica.Controllers;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pruebatecnica.camilo.pruebatecnica.DataBase.SqlDbHelper;
import com.pruebatecnica.camilo.pruebatecnica.Models.Person;
import com.pruebatecnica.camilo.pruebatecnica.Models.Vehicle;
import com.pruebatecnica.camilo.pruebatecnica.R;

import java.util.ArrayList;


public class Activity_insert_vehicle extends AppCompatActivity {
    private SqlDbHelper DatabaseHelper = new SqlDbHelper(this);
    public Button btInsertVehicle;
    public EditText etVehicularPlate,etBrand,etModel,etDoorsNumber,etType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_vehicle);

        etVehicularPlate = findViewById(R.id.etVehicularPlate);
        etBrand = findViewById(R.id.etBrand);
        etModel = findViewById(R.id.etModel);
        etDoorsNumber = findViewById(R.id.etDoorsNumber);
        etType = findViewById(R.id.etType);

        btInsertVehicle = findViewById(R.id.btSaveVehicle);
        btInsertVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(InsertNewVehicle()){
                    Snackbar.make(v, "El vehiculo se insertó correctamente", Snackbar.LENGTH_LONG)
                           .setAction("Action", null).show();
                }else{
                    Snackbar.make(v, "Se presento un error al insertar vehiculo", Snackbar.LENGTH_LONG)
                           .setAction("Action", null).show();

                }
            }
        });
    }

    private boolean InsertNewVehicle(){

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(etBrand.getText().toString());
        vehicle.setDoorNumber(Integer.parseInt(etDoorsNumber.getText().toString()));
        vehicle.setModel(etModel.getText().toString());
        vehicle.setVehicleType(etType.getText().toString());
        vehicle.setVehicularPlate(etVehicularPlate.getText().toString());

        return DatabaseHelper.InsertVehicle(vehicle);
    }

}
