package com.findfriend.ui;

import com.findfriend.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Before
    public void setup() {
        Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void  addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}
