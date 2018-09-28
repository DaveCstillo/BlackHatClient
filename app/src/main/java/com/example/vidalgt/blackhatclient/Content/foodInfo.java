package com.example.vidalgt.blackhatclient.Content;


import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class foodInfo {

    public static final List<foodItem> ITEMS = new ArrayList<foodItem>();
    public static final Map<String, foodItem> ITEM_MAP = new HashMap<String, foodItem>();

    static{
//        for (int i = 1; i <= 10; i++) {
//            addItem(createFoodInfo(i,String.valueOf(i),i));
//        }
    }

    public static  void addItem(foodItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id), item);
    }

    public static foodItem createFoodInfo( int id, String nombre, String imgId,int precio, String categoria){
        return new foodItem(id, nombre,imgId,precio,categoria);
    }


    public static void clearList(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }


    public static class foodItem{
        public final int id;
        public final String name;
        public final String imgID;
        public final int precio;
        public final String categoria;
        Bitmap Imagen;

        public foodItem(int id, String name, String imgId, int precio, String categoria) {
            this.id = id;
            this.name = name;
            this.imgID = imgId;
            this.precio = precio;
            this.categoria = categoria;

        }

        @Override
        public String toString() {
            return super.toString();
        }


        public Bitmap getImagen() {
            return Imagen;
        }

        public void setImagen(Bitmap imagen) {
            Imagen = imagen;
        }
    }

}
