package com.hmmelton.kibbble.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.Pet;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by harrison on 4/16/17.
 * This is an adapter class for a list containing adoptable pets.
 */

public class AdoptablePetsAdapter extends RecyclerView.Adapter<AdoptablePetsAdapter.ViewHolder> {

    private List<Pet> mPets; // Adoptable pets to display

    /**
     * Constructor
     * @param pets list of adoptable pets to display
     */
    public AdoptablePetsAdapter(List<Pet> pets) {
        mPets = pets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate layout resource
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        // Pass layout to view holder
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        // TODO: change this to mPets.size()
        return 4;
    }

    /**
     * This method adds a Pet object to the end of the adapter.
     * @param pet Pet to be added
     */
    public void addView(Pet pet) {
        mPets.add(pet);
    }

    /**
     * View holder class for enclosing RecyclerView
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
