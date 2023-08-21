package com.example.mediatekformationmobile.vue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediatekformationmobile.R;
import com.example.mediatekformationmobile.controleur.Controle;
import com.example.mediatekformationmobile.modele.Formation;

import java.util.ArrayList;

/**
 * Classe de gestion de la liste 'adapter'
 */
public class FormationListAdapter extends RecyclerView.Adapter<FormationListAdapter.ViewHolder> {

    private ArrayList<Formation> lesFormations;
    private Context context;
    private Controle controle;

    /**
     * Constructeur : valorise les propriétés privées
     * @param lesFormations
     * @param context
     */
    public FormationListAdapter(ArrayList<Formation> lesFormations, Context context) {
        this.lesFormations = lesFormations;
        this.context = context;
        this.controle = Controle.getInstance();
    }

    /**
     * Création d'une ligne d'affichage dans la liste "adapter"
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public FormationListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context parentContext = parent.getContext();
        LayoutInflater layout = LayoutInflater.from(parentContext);
        View view = layout.inflate(R.layout.layout_liste_formations, parent, false);
        return new FormationListAdapter.ViewHolder(view);
    }

    /**
     * Validation du contenu des objets graphiques
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtListeTitle.setText(lesFormations.get(position).getTitle());
        holder.txtListPublishedAt.setText(lesFormations.get(position).getPublishedAtToString());
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return lesFormations.size();
    }

    /**
     * Ouvre la page du détail de la formation
     * @param position
     */
    private void ouvrirUneFormationActivity(int position){
        controle.setFormation(lesFormations.get(position));
        Intent intent = new Intent(context, UneFormationActivity.class);
        context.startActivity(intent);
    }

    /**
     * Propriétés de la ligne
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageButton btnListFavori;
        public final TextView txtListPublishedAt;
        public final TextView txtListeTitle;

        /**
         * Constructeur : crée un lien avec les objets graphiques de la ligne
         * et gère les événements sur ces objets graphiques
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtListeTitle = (TextView)itemView.findViewById(R.id.txtListTitle);
            txtListPublishedAt = (TextView)itemView.findViewById(R.id.txtListPublishedAt);
            btnListFavori = (ImageButton)itemView.findViewById(R.id.btnListFavori);
            txtListeTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ouvrirUneFormationActivity(getAdapterPosition());
                }
            });
            txtListPublishedAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ouvrirUneFormationActivity(getAdapterPosition());
                }
            });
        }
    }

}

