package com.findfriend.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.view.MenuItem;

import androidx.navigation.NavController;

import com.findfriend.MainActivity;
import com.findfriend.app.R;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mActivity;

    @Test
    public void startActivity() {
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        mActivity = controller.setup().get();
        assertNotNull(mActivity);
    }

    @Test
    public void onNavigationItemSelected() {
        NavController navController = mock(mActivity.testOnlyGetNavController().getClass());
        MenuItem menuItem = mock(MenuItem.class);
        when(menuItem.getItemId()).thenReturn(R.id.item_favorite);
        verify(navController).navigate(R.id.item_favorite);
    }

}
