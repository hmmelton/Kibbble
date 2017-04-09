package com.hmmelton.kibbble.models;

import android.support.annotation.NonNull;

/**
 * Created by harrison on 4/9/17.
 * This class stores filters used to search for pets.
 */

public class Filters {

    // Properties
    private boolean[] mSexes;
    private boolean[] mSizes;
    private boolean[] mAges;

    public Filters(@NonNull boolean[] sexes, @NonNull boolean[] sizes, @NonNull boolean[] ages) {
        mSexes = sexes;
        mSizes = sizes;
        mAges = ages;
    }

    public boolean[] getSexes() {
        return mSexes;
    }

    public boolean[] getSizes() {
        return mSizes;
    }

    public boolean[] getAges() {
        return mAges;
    }
}
