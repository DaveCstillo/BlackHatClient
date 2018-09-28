package com.example.vidalgt.blackhatclient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Reservaciones extends Fragment {



    public Reservaciones() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Reservaciones newInstance(String param1, String param2) {
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
        return inflater.inflate(R.layout.fragment_reservaciones, container, false);
    }

}
