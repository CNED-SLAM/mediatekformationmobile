package com.example.mediatekformationmobile.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mediatekformationmobile.R;
import com.example.mediatekformationmobile.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * initialisations
     */
    private void init(){
        Controle.getInstance();
        creerMenu();
    }

    /**
     * appelle les procédures événementielles pour gérer le menu
     */
    private void creerMenu(){
        ecouteMenu((ImageButton)findViewById(R.id.btnFormations), FormationsActivity.class);
    }

    /**
     * procédure événementielle sur le clic d'une image du menu
     * @param btn
     * @param classe
     */
    private void ecouteMenu(ImageButton btn, final Class classe){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = MainActivity.this;
                Intent intent = new Intent(activity, classe);
                activity.startActivity(intent);
            }
        });
    }

}