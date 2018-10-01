package com.example.vidalgt.blackhatclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vidalgt.blackhatclient.Content.foodInfo;
import com.google.gson.JsonElement;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class foodDisplayFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    ProgressDialog progress;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public foodDisplayFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static foodDisplayFragment newInstance(int columnCount) {
        foodDisplayFragment fragment = new foodDisplayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fooddisplay_list, container, false);

        // Set the adapter

            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));
            progress = new ProgressDialog(getContext());
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
            foodInfo.clearList();
            pedirCategoria(recyclerView);

        return view;
    }

    public void pedirCategoria(RecyclerView recyclerView){
         new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson("getcategories.php"), (json,exception)->{

             if(exception!=null){
                 exception.printStackTrace();
             }
             if(json!=null){
                 for(JsonElement res : json.getAsJsonObject().get("Categorias").getAsJsonArray()) {
                        int ID;
                        String name;
                     if(res.getAsJsonObject().get("Result")!=null){

                     }else {
                         ID = res.getAsJsonObject().get("ID").getAsInt();
                         name = res.getAsJsonObject().get("Nombre").getAsString();
                         pedirComida(name, recyclerView);
                     }
                 }
             }

         }).execute();
    }


    public void pedirComida(String nombreTabla,RecyclerView recyclerView){
        nombreTabla = nombreTabla.replace(" ", "_");
        final String tablaName = nombreTabla;
        StringBuilder path = new StringBuilder("getAllProducts.php");

        String finalurl = path.toString();

        Log.d("URL", finalurl);

        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(finalurl), (json, exception)->{
            progress.dismiss();

            if(exception!=null){
                Log.d("Exception",exception.getMessage());
            }
            if(json!=null){
              //  JsonObject object = json.getAsJsonObject();
               // JsonArray array = object.getAsJsonArray("Bebidas_Frias");

                for(JsonElement res : json.getAsJsonObject().get(tablaName).getAsJsonArray()){
                    int Id, Precio;
                    String Nombre, Imagen;


                    if(res.getAsJsonObject().get("Result")!=null){

                    }else{
                        Id = res.getAsJsonObject().get("ID").getAsInt();
                        Nombre = res.getAsJsonObject().get("Nombre").getAsString();
                        Imagen = res.getAsJsonObject().get("Imagen").getAsString();
                        Precio = res.getAsJsonObject().get("Precio").getAsInt();

                        createNewfoodItem(Id, Nombre, Imagen, Precio,tablaName, recyclerView);


                    }



                }
            }


        }).execute();

    }


    public void createNewfoodItem(int Id, String Nombre, String Imagen, int Precio,String categoria,RecyclerView recyclerView){
        foodInfo.addItem(foodInfo.createFoodInfo(Id,Nombre,Imagen,Precio,categoria));

        recyclerView.setAdapter(new foodDisplayRecyclerViewAdapter(foodInfo.ITEMS,mListener));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(foodInfo.foodItem item);
    }
}
