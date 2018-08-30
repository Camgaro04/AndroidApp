package com.pruebatecnica.camilo.pruebatecnica.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pruebatecnica.camilo.pruebatecnica.Models.Person;
import com.pruebatecnica.camilo.pruebatecnica.Models.Vehicle;

import java.util.ArrayList;


public class SqlDbHelper extends SQLiteOpenHelper {

    public static final String  DATABASE_NAME = "AppDevAndroid.db";
    public static final int DATABASE_VERSION =1;


    public String sqlString = null;


    public  SqlDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CreateTablePersons = "CREATE TABLE "+DataBaseContract.TABLE_PERSONS_NAME+" ( "
                +DataBaseContract.PER_PERSONS_IDENTIFICATION+" TEXT NOT NULL,"
                +DataBaseContract.PER_PERSONS_NAME +" TEXT NOT NULL,"
                +DataBaseContract.PER_PERSONS_LAST_NAME +" TEXT NOT NULL,"
                +DataBaseContract.PER_PERSONS_IS_MARRIED +" TEXT NOT NULL,"
                +DataBaseContract.PER_PERSONS_BORN_DATE+" TEXT NOT NULL,"
                +DataBaseContract.PER_PERSONS_MONTH_BALANCE + " TEXT NOT NULL,"
                +DataBaseContract.PER_PERSONS_PROFESSION + " TEXT NOT NULL,"
                +DataBaseContract.PER_PERSONS_VEHICLE+" TEXT NOT NULL );";

         String CreateTableVehicles ="CREATE TABLE "+DataBaseContract.TABLE_VEHICLES_NAME+" ("
                +DataBaseContract.VEH_VEHICLE_PLATE+" TEXT NOT NULL,"
                +DataBaseContract.VEH_VEHICLE_BAND + " TEXT NOT NULL,"
                +DataBaseContract.VEH_VEHICLE_DOORS_NUMBER +" TEXT NOT NULL,"
                +DataBaseContract.VEH_VEHICLE_MODEL+" TEXT NOT NULL,"
                +DataBaseContract.VEH_VEHICLE_TYPE +" TEXT NOT NULL )";

        db.execSQL(CreateTableVehicles);
        db.execSQL(CreateTablePersons);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table "+DataBaseContract.TABLE_VEHICLES_NAME);
        db.execSQL("Drop table "+DataBaseContract.TABLE_PERSONS_NAME);
        onCreate(db);
    }

    public boolean InsertVehicle (Vehicle vehicle)
    {
        try{

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DataBaseContract.VEH_VEHICLE_PLATE, vehicle.getVehicularPlate());
            values.put(DataBaseContract.VEH_VEHICLE_BAND, vehicle.getBrand());
            values.put(DataBaseContract.VEH_VEHICLE_DOORS_NUMBER , vehicle.getDoorNumber());
            values.put(DataBaseContract.VEH_VEHICLE_MODEL, vehicle.getModel());
            values.put(DataBaseContract.VEH_VEHICLE_TYPE,vehicle.getVehicleType());

            long result = db.insert(DataBaseContract.TABLE_VEHICLES_NAME,null,values);

            if(result == -1){
                return false;

            }else{
                return true;
            }

        }catch (Exception ex){
            return false;
        }

    }

    public boolean InsertPerson(Person person){

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues valuesPersons= new ContentValues();
            valuesPersons.put(DataBaseContract.PER_PERSONS_IDENTIFICATION,person.getIdentification());
            valuesPersons.put(DataBaseContract.PER_PERSONS_NAME,person.getNames());
            valuesPersons.put(DataBaseContract.PER_PERSONS_LAST_NAME,person.getLastName());
            valuesPersons.put(DataBaseContract.PER_PERSONS_IS_MARRIED,person.isMarried());
            valuesPersons.put(DataBaseContract.PER_PERSONS_BORN_DATE,person.getBornDate());
            valuesPersons.put(DataBaseContract.PER_PERSONS_MONTH_BALANCE, person.getMonthBalance());
            valuesPersons.put(DataBaseContract.PER_PERSONS_PROFESSION,person.getProfession());
            valuesPersons.put(DataBaseContract.PER_PERSONS_VEHICLE,person.getActual_vehicle_plate());

            long result= db.insert(DataBaseContract.TABLE_PERSONS_NAME,null,valuesPersons);

            if(result == -1){
                return false;

            }else{
                return true;
            }

        }catch (Exception ex){
            Log.e("Exeption",ex.getMessage());
            return false;
        }
    }


    public ArrayList<Person> GetAllPersons(){

        ArrayList<Person> personArrayList = new ArrayList<>();

        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("Select * from "+DataBaseContract.TABLE_PERSONS_NAME,null);
            while (c.moveToNext()){
                Person person = new Person();
                person.setIdentification(c.getString(c.getColumnIndex(DataBaseContract.PER_PERSONS_IDENTIFICATION)));
                person.setNames(c.getString(c.getColumnIndex(DataBaseContract.PER_PERSONS_NAME)));
                person.setLastName(c.getString(c.getColumnIndex(DataBaseContract.PER_PERSONS_LAST_NAME)));
                person.setBornDate(c.getString(c.getColumnIndex(DataBaseContract.PER_PERSONS_BORN_DATE)));
                person.setProfession(c.getString(c.getColumnIndex(DataBaseContract.PER_PERSONS_PROFESSION)));
                person.setActual_vehicle_plate(c.getString(c.getColumnIndex(DataBaseContract.PER_PERSONS_VEHICLE)));
                String isMarried = (c.getString(c.getColumnIndex(DataBaseContract.PER_PERSONS_IS_MARRIED)));
                if(isMarried.equals("1")){
                    person.setMarried(true);
                }else{
                    person.setMarried(false);
                }

                person.setActualVehicle(GetVehicleById(c.getString(c.getColumnIndex(DataBaseContract.PER_PERSONS_VEHICLE))));
                personArrayList.add(person);
            }
            return personArrayList;

        } catch (Exception ex){
            Log.e("Exeption",ex.getMessage());
            return null;
        }

    }

    public Vehicle GetVehicleById(String vehicularPlate){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Vehicle vehicle = null;
            Cursor c = db.rawQuery("Select * from "+DataBaseContract.TABLE_VEHICLES_NAME
                    + " Where "+DataBaseContract.VEH_VEHICLE_PLATE +" = '"+vehicularPlate+"';",null);
            while(c.moveToNext()){
                vehicle = new Vehicle();
                vehicle.setVehicularPlate(c.getString(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_PLATE)));
                vehicle.setBrand(c.getString(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_BAND)));
                vehicle.setModel(c.getString(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_MODEL)));
                vehicle.setDoorNumber(c.getInt(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_DOORS_NUMBER)));
                vehicle.setVehicleType(c.getString(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_TYPE)));;
            }

            return vehicle;
        }catch (Exception ex){
            Log.e("Exeption",ex.getMessage());
          return null;
        }

    }

    public ArrayList<Vehicle> GetAllVehicles(){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("Select * from "+DataBaseContract.TABLE_VEHICLES_NAME, null);
            ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();

            while (c.moveToNext()){
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicularPlate(c.getString(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_PLATE)));
                vehicle.setBrand(c.getString(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_BAND)));
                vehicle.setModel(c.getString(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_MODEL)));
                vehicle.setDoorNumber(c.getInt(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_DOORS_NUMBER)));
                vehicle.setVehicleType(c.getString(c.getColumnIndex(DataBaseContract.VEH_VEHICLE_TYPE)));

                vehicleArrayList.add(vehicle);

            }

            return vehicleArrayList;
        }catch (Exception ex){
            Log.e("Exeption",ex.getMessage());
            return null;
        }
    }
}
