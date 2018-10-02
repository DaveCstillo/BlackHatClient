package com.example.vidalgt.blackhatclient;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vidalgt.blackhatclient.Content.feedContent;
import com.example.vidalgt.blackhatclient.Content.feedContent.feedItem;
import com.example.vidalgt.blackhatclient.notificationContentFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link feedItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class notificationContentRVA extends RecyclerView.Adapter<notificationContentRVA.ViewHolder> {

    private final List<feedItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public notificationContentRVA(List<feedItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notificationcontent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        ponerImagen(mValues.get(position).imgName, holder.mImageView, position);
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).Titulo);
        holder.mContentView.setText(mValues.get(position).Descripcion);
        holder.mImageView.setImageBitmap(mValues.get(position).getImagen());

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
        Images.feedApi();
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
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public feedItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.titleNotif);
            mContentView = view.findViewById(R.id.contentNotif);
            mImageView = view.findViewById(R.id.imagenNotif);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
