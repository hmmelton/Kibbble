package com.hmmelton.kibbble.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindDimen
import butterknife.BindView
import butterknife.ButterKnife
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hmmelton.kibbble.R
import com.hmmelton.kibbble.adapters.AdoptablePetsAdapter
import com.hmmelton.kibbble.models.Pet
import com.hmmelton.kibbble.views.GridSpaceItemDecoration
import java.util.ArrayList

/**
 * Created by harrisonmelton on 4/30/17.
 * Fragment for the main page
 */
class MainFragment : Fragment() {

    @Suppress("unused")
    private val TAG = javaClass.simpleName
    // Firebase references
    private val DATABASE = FirebaseDatabase.getInstance()
    private val DB_REF = DATABASE.reference.child("pets").child("dogs")

    private var mAdapter: AdoptablePetsAdapter? = null

    @BindView(R.id.rv_main_fragment)
    internal var mRecyclerView: RecyclerView? = null

    @BindDimen(R.dimen.grid_spacing)
    internal var mGridSpacing: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
        ButterKnife.bind(this, rootView)

        setUpRecyclerView()
        addRecyclerViewData()

        return rootView
    }

    /**
     * This menu sets up the fragment's RecyclerView.
     */
    private fun setUpRecyclerView() {
        val NUM_COLS = 2
        // Create RecyclerView parts
        mAdapter = AdoptablePetsAdapter(ArrayList<Pet>())
        val layoutManager = GridLayoutManager(context, NUM_COLS)
        val itemDecoration = GridSpaceItemDecoration(mGridSpacing, NUM_COLS)

        // Set RecyclerView parts
        mRecyclerView!!.adapter = mAdapter
        mRecyclerView!!.layoutManager = layoutManager
        mRecyclerView!!.addItemDecoration(itemDecoration)
    }

    /**
     * This method displays data from Firebase in the fragment.
     */
    private fun addRecyclerViewData() {
        // TODO: Add pets to view
        DB_REF.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children
                        .asSequence()
                        .map {
                            // Deserialize Pet object
                            it.getValue(Pet::class.java)
                            // TODO: Add pet card to swipe view
                        }
                        .forEach { mAdapter!!.addView(it) }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // There was an error fetching pets from Firebase
                Toast.makeText(context, R.string.error_fetching_pets,
                        Toast.LENGTH_LONG).show()
            }
        })
    }

    /**
     * Singleton that creates and returns new instance of this fragment.
     */
    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}