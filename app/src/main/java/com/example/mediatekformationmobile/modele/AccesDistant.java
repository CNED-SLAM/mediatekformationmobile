package com.example.mediatekformationmobile.modele;

import android.util.Log;

import com.example.mediatekformationmobile.controleur.Controle;
import com.example.mediatekformationmobile.outils.AccesREST;
import com.example.mediatekformationmobile.outils.AsyncResponse;
import com.example.mediatekformationmobile.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class AccesDistant implements AsyncResponse {

    private static final String SERVERADDR = "http://192.168.0.16/rest_mediatekformationmobile/";
    private static AccesDistant instance;
    private Controle controle;

    /**
     * constructeur oruvé
     */
    private AccesDistant(){
        controle = Controle.getInstance();
    }

    /**
     * Création d'une instance unique de la classe
     * @return instance unique de la classe
     */
    public static AccesDistant getInstance(){
        if(instance == null){
            instance = new AccesDistant();
        }
        return instance;
    }

    /**
     * retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output);
        try {
            JSONObject retour = new JSONObject(output);
            String code = retour.getString("code");
            String message = retour.getString("message");
            String result = retour.getString("result");
            if(!code.equals("200")){
                Log.d("erreur", "************ retour serveur code="+code+" result="+result);
            }else{
                try{
                    JSONArray resultJson = new JSONArray(result);
                    ArrayList<Formation> lesFormations = new ArrayList<Formation>();
                    for(int k=0;k<resultJson.length();k++) {
                        JSONObject info = new JSONObject(resultJson.get(k).toString());
                        int id = Integer.parseInt(info.getString("id"));
                        Date publishedAt = MesOutils.convertStringToDate(info.getString("published_at"),
                                "yyyy-MM-dd hh:mm:ss");
                        String title = info.getString("title");
                        String description = info.getString("description");
                        String videoId = info.getString("video_id");
                        Formation formation = new Formation(id, publishedAt, title, description, videoId);
                        lesFormations.add(formation);
                    }
                    controle.setLesFormations(lesFormations);
                }catch (JSONException ex){}
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * envoi de données vers le serveur distant
     * @param operation
     * @param table
     * @param lesDonneesJSON
     */
    public void envoi(String operation, String table, JSONObject lesDonneesJSON){
        AccesREST accesDonnees = new AccesREST();
        accesDonnees.delegate = this;
        String requesMethod = null;
        switch (operation){
            case "tous" : requesMethod="GET"; break;
        }
        if (requesMethod != null) {
            accesDonnees.setRequestMethod(requesMethod);
            accesDonnees.addParam(table);
            if(lesDonneesJSON != null && lesDonneesJSON.length() > 0) {
                accesDonnees.addParam(lesDonneesJSON.toString());
            }
            accesDonnees.execute(SERVERADDR);
        }
    }

}
