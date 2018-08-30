package com.pruebatecnica.camilo.pruebatecnica.Controllers;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pruebatecnica.camilo.pruebatecnica.DataBase.SqlDbHelper;
import com.pruebatecnica.camilo.pruebatecnica.Models.Person;
import com.pruebatecnica.camilo.pruebatecnica.Models.Vehicle;
import com.pruebatecnica.camilo.pruebatecnica.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_insert_person extends AppCompatActivity {

    private SqlDbHelper DatabaseHelper = new SqlDbHelper(this);
    public TextView edtName,edtLastName,edtBornDate,edtIdentification,edtProfession,edtMonthBalance,edtActualVehiclePlate;
    public Spinner spIsMarried;
    public Button btnSavePerson;
    private String selectedState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_person);


        spIsMarried = findViewById(R.id.spIsMarried);
        List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add("Si");
        spinnerArray.add("No");

        edtName = findViewById(R.id.etNames);
        edtLastName = findViewById(R.id.etLastName);
        edtBornDate = findViewById(R.id.etBornDate);
        edtIdentification = findViewById(R.id.etIdentification);
        edtProfession = findViewById(R.id.etProfession);
        edtMonthBalance = findViewById(R.id.etMonthBalance);
        edtActualVehiclePlate = findViewById(R.id.etActualVehiclePlate);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,spinnerArray);
        spIsMarried.setAdapter(arrayAdapter);
        spIsMarried.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedState = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSavePerson = findViewById(R.id.btSavePerson);
        btnSavePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(InsertPerson()){
                    Snackbar.make(view, "La persona se inserto correctamente", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(view, "Se presento un error al insertar la persona", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private boolean InsertPerson(){

        Person person = new Person();
        person.setNames(edtName.getText().toString());
        person.setLastName(edtLastName.getText().toString());
        person.setBornDate(edtBornDate.getText().toString());
        person.setProfession(edtProfession.getText().toString());
        person.setMonthBalance(Integer.parseInt(edtMonthBalance.getText().toString()));
        person.setIdentification(edtIdentification.getText().toString());
        person.setActual_vehicle_plate(edtActualVehiclePlate.getText().toString());

        if(selectedState.equals("si")){
            person.setMarried(true);
        }else{
            person.setMarried(false);
        }
        Vehicle vehicle = DatabaseHelper.GetVehicleById(edtActualVehiclePlate.getText().toString());
        if(vehicle != null){
            person.setActualVehicle(vehicle);
        }



        return DatabaseHelper.InsertPerson(person);
    }

}
