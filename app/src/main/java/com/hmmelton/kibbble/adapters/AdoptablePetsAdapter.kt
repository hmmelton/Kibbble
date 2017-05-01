package com.hmmelton.kibbble.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.hmmelton.kibbble.R
import com.hmmelton.kibbble.models.Pet

/**
 * Created by harrisonmelton on 4/30/17.
 * This is an adapter class for a list containing adoptable pets.
 */
class AdoptablePetsAdapter(private val mPets: MutableList<Pet>) :
        RecyclerView.Adapter<AdoptablePetsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate layout resource
        val rootView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)

        // Pass layout to view holder
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get pet at given position
        val pet = mPets[position]
        // Set image
        val imageUrlString = pet.images?.get(0)
        if (imageUrlString != null) {
            Glide.with(holder.profileImageView?.context)
                    .load(imageUrlString)
                    .into(holder.profileImageView)
        }
        // Set name
        holder.nameAgeTextView?.text = pet.name + ", " + pet.age
        // Set gender
        holder.genderTextView?.text = pet.gender
    }

    override fun getItemCount(): Int {
        return mPets.size
    }

    /**
     * This method adds a Pet object to the end of the adapter.
     * @param pet Pet to be added
     */
    fun addView(pet: Pet) {
        mPets.add(pet)
        notifyItemInserted(mPets.size - 1)
    }

    /**
     * View holder class for enclosing RecyclerView
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.iv_card_item)
        var profileImageView: ImageView? = null
        @BindView(R.id.tv_name_age_card_item)
        var nameAgeTextView: TextView? = null
        @BindView(R.id.tv_gender_card_item)
        var genderTextView: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
