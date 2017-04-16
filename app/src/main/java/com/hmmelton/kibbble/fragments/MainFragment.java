package com.hmmelton.kibbble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.Pet;
import com.hmmelton.kibbble.views.Card;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

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

    @BindView(R.id.swipe_view)
    SwipePlaceHolderView mSwipeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        // Create swipe view
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f));

        // TODO: Add pets to view
        DB_REF.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Pet pet = dataSnapshot.getValue(Pet.class);
                for (DataSnapshot petSnapshot : dataSnapshot.getChildren()) {
                    // Deserialize Pet object
                    Pet pet = petSnapshot.getValue(Pet.class);
                    // Add pet card to swipe view
                    mSwipeView.addView(new Card(getContext(), pet, mSwipeView));
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
