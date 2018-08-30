package com.pruebatecnica.camilo.pruebatecnica.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pruebatecnica.camilo.pruebatecnica.Models.Person;
import com.pruebatecnica.camilo.pruebatecnica.R;

import java.util.ArrayList;

public class Adapter_persons extends BaseAdapter{

    private ArrayList<Person> personArrayList;
    private Context context;

    public Adapter_persons(Context context,ArrayList<Person> personArrayList){
        this.context = context;
        this.personArrayList = personArrayList;
    }


    @Override
    public int getCount() {
        return personArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return personArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return personArrayList.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(null == convertView){
            convertView = inflater.inflate(R.layout.adapter_person_custom_row,parent,false);
        }

        TextView tvName = convertView.findViewById(R.id.tvNamesValue);
        TextView tvLastName = convertView.findViewById(R.id.tvLastName);
        TextView tvBornDate = convertView.findViewById(R.id.tvBornDate);
        TextView tvIdentification = convertView.findViewById(R.id.tvIdentification);
        TextView tvProfession = convertView.findViewById(R.id.tvProfession);
        TextView tvIsMarried = convertView.findViewById(R.id.tvIsMaried);
        TextView tvActualVehicle = convertView.findViewById(R.id.tvActualVehicle);

        Person person = (Person) getItem(position);

        if(person != null){
            tvName.setText(person.getNames());
            tvLastName.setText(person.getLastName());
            tvBornDate.setText(person.getBornDate());
            tvIdentification.setText(person.getIdentification());
            tvProfession.setText(person.getProfession());

            if(person.isMarried()){
                tvIsMarried.setText("Si");
            }else{
                tvIsMarried.setText("No");
            }

            if(person.getActualVehicle() != null){
                tvActualVehicle.setText(person.getActualVehicle().getVehicularPlate());
            }else{
                tvActualVehicle.setText(person.getActual_vehicle_plate()+"(Vehiculo no registrado)");
            }

        }

        return convertView;
    }
}
