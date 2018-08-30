package com.pruebatecnica.camilo.pruebatecnica.Adapter;


import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.pruebatecnica.camilo.pruebatecnica.Models.Vehicle;
import com.pruebatecnica.camilo.pruebatecnica.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Vehicles extends BaseAdapter {

    Context context;
    ArrayList<Vehicle> vehicleArrayList;

    public Adapter_Vehicles (Context context,ArrayList<Vehicle> vehicleArrayList){
            this.context = context;
            this.vehicleArrayList = vehicleArrayList;
    }

    @Override
    public int getCount() {
        return vehicleArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return vehicleArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return vehicleArrayList.indexOf(position);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(null == convertView){
            convertView = inflater.inflate(R.layout.adapter_vehicle_custom_row,parent,false);
        }

        Vehicle vehicle = (Vehicle)getItem(position);

        TextView etVehicularPlate = convertView.findViewById(R.id.tvVehicularPlate);
        TextView etBrand = convertView.findViewById(R.id.tvBrand);
        TextView etModel = convertView.findViewById(R.id.tvModel);
        TextView etDoorNumber = convertView.findViewById(R.id.tvDoorsNumber);
        TextView etVehicularType = convertView.findViewById(R.id.tvVehicleType);

        if(vehicle != null){
            etVehicularPlate.setText(vehicle.getVehicularPlate());
            etBrand.setText(vehicle.getBrand());
            etModel.setText(vehicle.getModel());
            etDoorNumber.setText(String.valueOf(vehicle.getDoorNumber()));
            etVehicularType.setText(vehicle.getVehicleType());
        }

       return convertView;
    }


}
