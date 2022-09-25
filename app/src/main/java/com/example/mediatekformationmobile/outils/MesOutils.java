package com.example.mediatekformationmobile.outils;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface MesOutils {

    /**
     * reçoit une date au format String et la convertit au format Date
     * @param uneDate au format String
     * @param expectedPattern pour formater la date
     * @return date convertie au format Date
     */
    public static Date convertStringToDate(String uneDate, String expectedPattern){
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            return formatter.parse(uneDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * reçoit une date au format String et la convertit au format Date avec pattern précis
     * @param uneDate au format String
     * @return date convertie au format Date
     */
    public static Date convertStringToDate(String uneDate){
        String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy";
        return convertStringToDate(uneDate, expectedPattern);
    }

    /**
     * reçoit une date au format Date et la convertit au format String
     * @param uneDate au format Date
     * @return date convertie au format String
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(uneDate);
    }

    /**
     * Charge une imagge à partir d'une url
     * @param img
     * @param url
     */
    public static void loadMapPreview (ImageButton img, String url) {
        //start a background thread for networking
        new Thread(new Runnable() {
            public void run(){
                try {
                    //download the drawable
                    final Drawable drawable = Drawable.createFromStream((InputStream) new URL(url).getContent(), "src");
                    //edit the view in the UI thread
                    img.post(new Runnable() {
                        public void run() {
                            img.setImageDrawable(drawable);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

