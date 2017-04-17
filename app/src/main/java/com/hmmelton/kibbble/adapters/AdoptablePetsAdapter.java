package com.hmmelton.kibbble.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.Pet;

import java.util.List;

import butterknife.BindView;
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
        // Get pet at given position
        Pet pet = mPets.get(position);
        // Set image
        String imageUrlString = pet.getImages().get(0);
        if (imageUrlString != null) {
            Glide.with(holder.profileImageView.getContext())
                    .load(imageUrlString)
                    .into(holder.profileImageView);
        }
        // Set name
        holder.nameAgeTextView.setText(pet.getName() + ", " + pet.getAge());
        // Set gender
        holder.genderTextView.setText(pet.getGender());
    }

    @Override
    public int getItemCount() {
        return mPets.size();
    }

    /**
     * This method adds a Pet object to the end of the adapter.
     * @param pet Pet to be added
     */
    public void addView(Pet pet) {
        mPets.add(pet);
        notifyItemInserted(mPets.size() - 1);
    }

    /**
     * View holder class for enclosing RecyclerView
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_card_item)
        ImageView profileImageView;
        @BindView(R.id.tv_name_age_card_item)
        TextView nameAgeTextView;
        @BindView(R.id.tv_gender_card_item)
        TextView genderTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
