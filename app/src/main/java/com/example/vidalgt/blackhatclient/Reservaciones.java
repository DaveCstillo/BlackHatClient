package com.example.vidalgt.blackhatclient;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidalgt.blackhatclient.serverconnection.BackgroundTask;
import com.example.vidalgt.blackhatclient.serverconnection.httpHandler;
import com.google.gson.JsonElement;


public class Reservaciones extends BaseFragment {


    Button DateBtn, TimeBtn, cancel, ok, mapa;
    TextView fechaTxt, horaTxt;
    EditText nombre, correo;

    public Reservaciones() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Reservaciones newInstance() {
        Reservaciones fragment = new Reservaciones();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservaciones, container, false);

        DateBtn = view.findViewById(R.id.DatePickerBtn);
        TimeBtn = view.findViewById(R.id.TimePickerBtn);
        fechaTxt = view.findViewById(R.id.ResDate);
        horaTxt = view.findViewById(R.id.ResTime);
        nombre = view.findViewById(R.id.ResAnombreDTxt);
        correo = view.findViewById(R.id.ResCorreoTxt);
        cancel = view.findViewById(R.id.cancelBtn);
        ok = view.findViewById(R.id.OkBtn);
        mapa = view.findViewById(R.id.mapaBtn);


        DateBtn.setOnClickListener((v)->{
            showDatePickerDialog(view);
        });

        TimeBtn.setOnClickListener((v)->{
            showTimePickerDialog(view);
        });


        cancel.setOnClickListener((v)->{
            nombre.setText("");
            correo.setText("");
            fechaTxt.setText("");
            horaTxt.setText("");
            nombre.requestFocus();
            Toast toast = Toast.makeText(getContext(),"Reservacion cancelada",Toast.LENGTH_LONG);
            toast.show();
        });

        ok.setOnClickListener((v)->{
            callList("setReservacion.php?");

        });

        mapa.setOnClickListener((v)->{
            Intent intent = new Intent(getActivity(), MapsActivity.class);

            startActivity(intent);
        });

     return view;
    }



    public void showTimePickerDialog(View v) {
        MyTimePicker timePicker = new MyTimePicker();
        timePicker.setTexto(horaTxt);
        DialogFragment newFragment = timePicker;
        newFragment.show(getBaseActivity().getManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DatePicker datePicker = new DatePicker();
        datePicker.setTexto(fechaTxt);
        DialogFragment newFragment = datePicker;
        newFragment.show(getBaseActivity().getManager(), "datePicker");
    }


    public void callList(String path){

        String name = nombre.getText().toString();
        String names = name.replace(" ", "_");
        String mail = correo.getText().toString();
        String email = mail.replace(" ", "");



        StringBuilder url = new StringBuilder(path);
        url.append("nombre=");
        url.append(names);
        url.append("&correo=");
        url.append(email);
        url.append("&fecha=");
        url.append(fechaTxt.getText());
        url.append("&hora=");
        url.append(horaTxt.getText());

        String finalURL = url.toString();
        new BackgroundTask<JsonElement>(()->httpHandler.instance.getJson(finalURL), (json,exception)->{
            if(exception!=null)
            {

            }

            if(json!=null){
                Log.d("Reservacion", "Enviada.... Json: "+json);
            }


        }).execute();


    }


}
