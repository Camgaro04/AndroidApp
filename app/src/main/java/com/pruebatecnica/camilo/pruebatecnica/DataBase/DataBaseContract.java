package com.pruebatecnica.camilo.pruebatecnica.DataBase;

import android.provider.BaseColumns;

public abstract class DataBaseContract implements BaseColumns {

    public static final String TABLE_VEHICLES_NAME = "Vehicle";
    public static final String TABLE_PERSONS_NAME = "Persons";

    public static final String VEH_VEHICLE_PLATE= "veh_vehiclePlate";
    public static final String VEH_VEHICLE_BAND = "veh_brand";
    public static final String VEH_VEHICLE_MODEL = "veh_model";
    public static final String VEH_VEHICLE_DOORS_NUMBER= "veh_doors_number";
    public static final String VEH_VEHICLE_TYPE = "veh_type";

    public static final String PER_PERSONS_NAME ="per_names";
    public static final String PER_PERSONS_IDENTIFICATION ="per_identification";
    public static final String PER_PERSONS_LAST_NAME= "per_last_name";
    public static final String PER_PERSONS_BORN_DATE = "per_born_date";
    public static final String PER_PERSONS_IS_MARRIED ="per_isMarried";
    public static final String PER_PERSONS_MONTH_BALANCE ="per_month_balance";
    public static final String PER_PERSONS_PROFESSION = "per_profession";
    public static final String PER_PERSONS_VEHICLE ="per_vehicle";

}
