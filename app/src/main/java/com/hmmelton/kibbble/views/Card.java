package com.hmmelton.kibbble.views;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hmmelton.kibbble.R;
import com.hmmelton.kibbble.models.Pet;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

/**
 * Created by harrisonmelton on 3/18/17.
 * This class represents a Card to be swiped.
 * ~ Taken from:
 * https://blog.mindorks.com/android-tinder-swipe-view-example-3eca9b0d4794#.4vw87sfd0 ~
 */

@Layout(R.layout.card_view)
public class Card {

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    private Pet mPet;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public Card(Context context, Pet pet, SwipePlaceHolderView swipeView) {
        mContext = context;
        mPet = pet;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved(){
        Glide.with(mContext).load(mPet.getImages().get(0)).into(profileImageView);
        nameAgeTxt.setText(mPet.getName() + ", " + mPet.getAge());
        locationNameTxt.setText(mPet.getGender());
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        // Save profile to global collection
        // TODO: save pet profile
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }
}
