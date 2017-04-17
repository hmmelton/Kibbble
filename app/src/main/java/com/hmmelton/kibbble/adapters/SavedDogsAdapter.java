package com.hmmelton.kibbble.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hmmelton.kibbble.DogProfileActivity;
import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.Pet;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by harrisonmelton on 3/19/17.
 * Adapter for saved dogs.
 */

public class SavedDogsAdapter extends RecyclerView.Adapter<SavedDogsAdapter.ViewHolder> {

    private List<Pet> mDogs;

    public SavedDogsAdapter(List<Pet> dogs) {
        this.mDogs = dogs;
    }

    public void updateList(List<Pet> dogs) {
        this.mDogs = dogs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_dog_cell, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pet dog = mDogs.get(position);
        Glide.with(holder.imageView.getContext())
                .load(dog.getImages().get(0))
                .placeholder(R.drawable.ic_profile_placeholder)
                .into(holder.imageView);

        // OnClickListener for views
        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.imageView.getContext(), DogProfileActivity.class);
            intent.putExtra("profile", mDogs.get(position));
            holder.imageView.getContext().startActivity(intent);
        });

        holder.name.setText(dog.getName());
        holder.info.setText(String.format("%s, %s, %s", dog.getGender(), dog.getBreed(),
                dog.getAge()));
    }

    @Override
    public int getItemCount() {
        return mDogs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name, info;

        public ViewHolder(View itemView) {
            super(itemView);
            // TODO: use ButterKnife?

            imageView = (CircleImageView) itemView.findViewById(R.id.saved_dog_image);
            name = (TextView) itemView.findViewById(R.id.saved_dog_name);
            info = (TextView) itemView.findViewById(R.id.saved_dog_info);
        }
    }
}
