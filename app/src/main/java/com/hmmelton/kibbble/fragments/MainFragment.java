package com.hmmelton.kibbble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.Pet;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisonmelton on 3/18/17.
 * Fragment for the main page
 */

public class MainFragment extends Fragment {

    @SuppressWarnings("unused")
    private final String TAG = getClass().getSimpleName();
    // Firebase references
    private final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();
    private final DatabaseReference DB_REF = DATABASE.getReference().child("pets").child("dogs");

    @BindView(R.id.tv_empty_list)
    TextView mEmptyListTextView;
    @BindView(R.id.rv_main_fragment)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        // TODO: Add pets to view
        DB_REF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Pet pet = dataSnapshot.getValue(Pet.class);
                for (DataSnapshot petSnapshot : dataSnapshot.getChildren()) {
                    // Deserialize Pet object
                    Pet pet = petSnapshot.getValue(Pet.class);
                    // TODO: Add pet card to swipe view
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // There was an error fetching pets from Firebase
                Toast.makeText(getContext(), R.string.error_fetching_pets,
                        Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
