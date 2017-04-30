package com.hmmelton.kibbble.models

/**
 * Created by harrisonmelton on 4/29/17.
 * Instances of this class act as filters for pets.
 */
class Filters(sexes: BooleanArray, sizes: BooleanArray, ages: BooleanArray) {
    // Properties
    private var mSexes: BooleanArray = sexes
    private var mSizes: BooleanArray = sizes
    private var mAges: BooleanArray = ages

    fun getSexes(): BooleanArray {
        return mSexes
    }

    fun getSizes(): BooleanArray {
        return mSizes
    }

    fun getAges(): BooleanArray {
        return mAges
    }
}