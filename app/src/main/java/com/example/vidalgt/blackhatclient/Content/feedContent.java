package com.example.vidalgt.blackhatclient.Content;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class feedContent {

    public static final List<feedItem> ITEMS = new ArrayList<feedItem>();
    public static final Map<String, feedItem> ITEM_MAP = new HashMap<String, feedItem>();

    static{

    }

    public static void addItem(feedItem item){
     ITEMS.add(item);
     ITEM_MAP.put(String.valueOf(item.id),item);
    }

    public static feedItem createFeedItem(int id, String titulo,String descripcion,String imgName){
        return new feedItem(id,titulo,descripcion,imgName);
    }

    public void clearList(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }




    public static class feedItem{
        public final int id;
        public final String Titulo,Descripcion,imgName;
        public Bitmap imagen;

        public feedItem(int id, String titulo, String descripcion, String imgName) {
            this.id = id;
            Titulo = titulo;
            Descripcion = descripcion;
            this.imgName = imgName;
        }

        @Override
        public String toString() {
            return super.toString();
        }


        public Bitmap getImagen() {
            return imagen;
        }

        public void setImagen(Bitmap imagen) {
            imagen = imagen;
        }
    }
}
