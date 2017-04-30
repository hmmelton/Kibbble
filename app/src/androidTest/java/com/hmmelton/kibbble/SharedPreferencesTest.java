package com.hmmelton.kibbble;

import android.support.test.runner.AndroidJUnit4;

import com.hmmelton.kibbble.models.Filters;
import com.hmmelton.kibbble.utils.SharedPrefsUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by harrison on 4/9/17.
 * Instrumentation test for SharedPreferences
 */
@RunWith(AndroidJUnit4.class)
public class SharedPreferencesTest {
    @Test
    public void saveAndFetchFilters() throws Exception {
        Filters filters = new Filters(
                new boolean[]{true, true}, // sexes
                new boolean[]{false, false, true}, // sizes
                new int[]{}); // ages
        // Save to local storage
        SharedPrefsUtil.savePetFilter(filters);
        // Fetch from local storage
        Filters filters1 = SharedPrefsUtil.getPetFilters();

        Assert.assertNotNull("oops, array is null", filters1);
        Assert.assertArrayEquals("oops, arrays are not equal",
                new boolean[]{false, false, true}, filters.getSizes());
    }
}
