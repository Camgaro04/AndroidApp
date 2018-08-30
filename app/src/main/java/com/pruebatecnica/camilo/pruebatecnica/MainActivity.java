package com.pruebatecnica.camilo.pruebatecnica;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pruebatecnica.camilo.pruebatecnica.Comunication.ServiceConnectionVolley;
import com.pruebatecnica.camilo.pruebatecnica.Controllers.Activity_persons;
import com.pruebatecnica.camilo.pruebatecnica.Controllers.Activity_vehicles;
import com.pruebatecnica.camilo.pruebatecnica.DataBase.SqlDbHelper;
import com.pruebatecnica.camilo.pruebatecnica.Models.Person;
import com.pruebatecnica.camilo.pruebatecnica.Models.Vehicle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


import static android.view.View.*;

public class MainActivity extends AppCompatActivity {

    public Button btnPersons,btnVehicles,btnSyncro;
    public EditText edtTime;
    private Context context;
    private final String BASE_URL="http://192.168.1.33:3000/usuario";
    private final String BASE_URL_VEHICULOS = "http://192.168.1.33:3000/Vehiculos";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPersons = findViewById(R.id.btPersons);
        btnVehicles = findViewById(R.id.btVehicles);
        btnSyncro = findViewById(R.id.btSync);
        edtTime = findViewById(R.id.etTime);
        context = this;
        pref = getSharedPreferences("Shared_preference_time", MODE_PRIVATE);
        editor = pref.edit();
        btnSyncro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String paramTime = edtTime.getText().toString();
                if(paramTime.isEmpty()){
                    edtTime.requestFocus();
                    return;
                }

                edtTime.requestFocus();
                editor.putString("param_time",paramTime);
                editor.apply();
                executePeriodicTask();
            }
        });

        btnVehicles.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchVechicles();
            }
        });
        btnPersons.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchPersons();
            }
        });

        this.context = this;
        executePeriodicTask();
    }

    private void LaunchPersons(){
        Intent launchPersons = new Intent (this, Activity_persons.class);
        startActivity(launchPersons);
    }

    private void LaunchVechicles (){
        Intent launchVehicles = new Intent(this, Activity_vehicles.class);
        startActivity(launchVehicles);
    }

    public void executePeriodicTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        String param_time = null;
        int time = 0 ;
        long timeMills = 0;

        if(pref != null){
            param_time = pref.getString("param_time",null);

        }
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            SyncData();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        if(param_time != null){
            time = Integer.parseInt(String.valueOf(param_time));
            timeMills = TimeUnit.MINUTES.toMillis(time);
            timer.schedule(doAsynchronousTask, 0, timeMills);
        }else{
            timer.schedule(doAsynchronousTask, 0, 50000);
        }

    }

    public void SyncData(){
        SqlDbHelper db = new SqlDbHelper(this);
        ArrayList<Person> dataPersons = db.GetAllPersons();
        Date currentTime = Calendar.getInstance().getTime();
        for(Person person : dataPersons){
           HashMap<String,String> dataParams = new HashMap<>();
           dataParams.put("Hora de sync",currentTime.toString());
           dataParams.put("names",person.getNames());
           dataParams.put("Last Name",person.getLastName());
           dataParams.put("BornDate",person.getBornDate());
           dataParams.put("Profession",person.getProfession());
           dataParams.put("is married",String.valueOf(person.isMarried()));
           dataParams.put("month balance",String.valueOf(person.getMonthBalance()));
           dataParams.put("Vehiculo Actual",person.getActualVehicle().toString());
            ServiceConnectionVolley serviceConnection = new ServiceConnectionVolley();
            serviceConnection.CallWithoutParams(this,dataParams,BASE_URL);
        }

        ArrayList<Vehicle> datavehicles = db.GetAllVehicles();
        for(Vehicle vehicle : datavehicles){
            HashMap <String,String> dataVehicleParams = new HashMap<>();
            dataVehicleParams.put("brand",vehicle.getBrand());
            dataVehicleParams.put("model",vehicle.getModel());
            dataVehicleParams.put("vehicle type",vehicle.getVehicleType());
            dataVehicleParams.put("vehicle plate",vehicle.getVehicularPlate());
            dataVehicleParams.put("Doors Number",String.valueOf(vehicle.getDoorNumber()));

            ServiceConnectionVolley serviceConnection = new ServiceConnectionVolley();
            serviceConnection.CallWithoutParams(this,dataVehicleParams,BASE_URL_VEHICULOS);
        }

    }

}




