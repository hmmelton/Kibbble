package com.hmmelton.kibbble.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.hmmelton.kibbble.DogProfileActivity
import com.hmmelton.kibbble.R
import com.hmmelton.kibbble.models.Pet
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by harrisonmelton on 4/30/17.
 * Adapter for saved dogs.
 */
class SavedDogsAdapter(private var mDogs: List<Pet>?) :
        RecyclerView.Adapter<SavedDogsAdapter.ViewHolder>() {

    fun updateList(dogs: List<Pet>) {
        this.mDogs = dogs
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.saved_dog_cell, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = mDogs!![position]
        Glide.with(holder.imageView?.context)
                .load(dog.images!![0])
                .placeholder(R.drawable.ic_profile_placeholder)
                .into(holder.imageView)

        // OnClickListener for views
        holder.imageView?.setOnClickListener { view ->
            val intent = Intent(holder.imageView?.context, DogProfileActivity::class.java)
            intent.putExtra("profile", mDogs!![position])
            holder.imageView?.context?.startActivity(intent)
        }

        holder.name?.text = dog.name
        holder.info?.text = String.format("%s, %s, %s", dog.gender, dog.breed,
                dog.age)
    }

    override fun getItemCount(): Int {
        return mDogs!!.size
    }

    /**
     * View holder class for enclosing RecyclerView
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.saved_dog_image)
        var imageView: CircleImageView? = null
        @BindView(R.id.saved_dog_name)
        var name: TextView? = null
        @BindView(R.id.saved_dog_info)
        var info: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}