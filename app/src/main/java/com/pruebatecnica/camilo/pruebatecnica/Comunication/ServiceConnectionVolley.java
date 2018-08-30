package com.pruebatecnica.camilo.pruebatecnica.Comunication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Map;

public class ServiceConnectionVolley {

    public ServiceConnectionVolley(){


    }

    public void CallWithoutParams(final Context context,final Map<String, String> params, String url){
        RequestQueue requestQueue =  Volley.newRequestQueue(context);
        StringRequest arrReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if(response != null){
                            Log.e("Respuesta",response);
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }

        ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        requestQueue.add(arrReq);
    }
}
