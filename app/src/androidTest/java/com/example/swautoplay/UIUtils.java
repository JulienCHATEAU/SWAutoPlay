package com.example.swautoplay;

import android.util.Log;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import junit.framework.Assert;

import static java.lang.String.valueOf;

/**
 * Created by simplygreenit on 21/10/18.
 */

public class UIUtils {

    private int ObjectAsChildById(String ID, UiDevice mDevice) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().resourceId(ID));
        Log.d("GSPT", "Nb child " + valueOf(object.getChildCount()));
        return object.getChildCount();
    }

    static boolean ClickById(String ID,UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().resourceId(ID));
        boolean exist = object.waitForExists(TIMEOUT);
        if (exist) {
            object.click();
        }
        return exist;
    }


    static void ClickByText(String Text,UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().textContains(Text));
        boolean exist = object.waitForExists(TIMEOUT);
        object.click();
    }

    private void FindWaitById(String ID,UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().resourceId(ID));
        boolean exist = object.waitForExists(TIMEOUT);
    }

    public static String GetTextById(String ID, UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().resourceId(ID));
        boolean exist = object.waitForExists(TIMEOUT);
        return object.getText();
    }

    public static String GetTextByText(String text, UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().textContains(text));
        boolean exist = object.waitForExists(TIMEOUT);
        return object.getText();
    }

    public static String GetTextByContainText(String text, UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().descriptionContains(text));
        boolean exist = object.waitForExists(TIMEOUT);
        return object.getContentDescription();
    }


    static void FindWaitByText(String text,UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().textContains(text));
        boolean exist = object.waitForExists(TIMEOUT);
    }

    static void FindWaitByContentDesc(String text,UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().descriptionContains(text));
        boolean exist = object.waitForExists(TIMEOUT);
    }

    static void ClickByContentDesc(String text,UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().descriptionContains(text));
        boolean exist = object.waitForExists(TIMEOUT);
        object.click();

    }

    static void SetTextById(String ID, String Text, UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().resourceId(ID));
        boolean exist = object.waitForExists(TIMEOUT);
        object.setText(Text);
    }

    static void SetTextByText(String TextID, String Text,UiDevice mDevice, int TIMEOUT) throws UiObjectNotFoundException {
        UiObject object = mDevice.findObject(new UiSelector().textContains(TextID));
        boolean exist = object.waitForExists(TIMEOUT);
        object.setText(Text);
    }

}
