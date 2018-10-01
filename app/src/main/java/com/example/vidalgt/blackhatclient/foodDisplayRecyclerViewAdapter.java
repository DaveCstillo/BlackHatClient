package com.example.vidalgt.blackhatclient;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vidalgt.blackhatclient.Content.foodInfo;
import com.example.vidalgt.blackhatclient.Content.foodInfo.foodItem;
import com.example.vidalgt.blackhatclient.serverconnection.BackgroundTask;
import com.example.vidalgt.blackhatclient.serverconnection.Images;
import com.example.vidalgt.blackhatclient.foodDisplayFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link foodInfo.foodItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class foodDisplayRecyclerViewAdapter extends RecyclerView.Adapter<foodDisplayRecyclerViewAdapter.ViewHolder> {

    private final List<foodItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public foodDisplayRecyclerViewAdapter(List<foodItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fooddisplay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ponerImagen(mValues.get(position).imgID, holder.imagen, position);
        holder.mItem = mValues.get(position);
        holder.mTxtView.setText(mValues.get(position).name);
        holder.imagen.setImageBitmap(mValues.get(position).getImagen());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void ponerImagen(String imgName, ImageView view, int position){
        Images.newApi(mValues.get(position).categoria);
        String imgurl = imgName;
        ImageView Imagen = view;

        new BackgroundTask<Bitmap>(() -> Images.get(imgurl), (b, e)->{

            if(e!=null){

            }
            if(b!=null){
                Log.d("Imagen", "Puesta");
                //view.setImageBitmap(b);
                Imagen.setImageBitmap(b);
                //view.setImageBitmap(mValues.get(position).getImagen());
            }


        }).execute();


    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imagen;
        public final TextView mTxtView;

        public foodItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTxtView = view.findViewById(R.id.exText);
            imagen = view.findViewById(R.id.imgFoodView);
        }

        @Override
        public String toString() {
            return super.toString() + " '"  + "'";
        }
    }
}
