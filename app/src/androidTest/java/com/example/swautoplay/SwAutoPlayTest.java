package com.example.swautoplay;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.uiautomator.Until;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SwAutoPlayTest {

    private static final int LAUNCH_TIMEOUT = 10000;
    private static final String SWAUTOPLAY_PACKAGE
            = "com.com2us.smon.normal.freefull.google.kr.android.common";

    @Test
    public void Play() throws InterruptedException {
        Context context = ApplicationProvider.getApplicationContext();

        // Initialize UiDevice instance
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // Start from the home screen
        device.pressHome();
        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(SWAUTOPLAY_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(SWAUTOPLAY_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);

        synchronized (device) {
            device.wait(50000);
        }

        device.click(20, 20);
    }
}
