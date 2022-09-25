package com.example.mediatekformationmobile.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.mediatekformationmobile.R;
import com.example.mediatekformationmobile.controleur.Controle;
import com.example.mediatekformationmobile.modele.Formation;

public class VideoActivity extends AppCompatActivity {

    /**
     * objet d'affichage de la vidéo
     */
    WebView wbvYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    /**
     * Affichage  de la vidéo
     */
    private void init(){
        Controle controle = Controle.getInstance();
        Formation formation = controle.getFormation();
        if(formation!=null) {
            wbvYoutube = findViewById(R.id.wbvYoutube);
            wbvYoutube.getSettings().setJavaScriptEnabled(true);
            wbvYoutube.setWebViewClient(new WebViewClient());
            wbvYoutube.loadUrl("https://www.youtube.com/embed/" + formation.getVideoId());
        }
    }

}