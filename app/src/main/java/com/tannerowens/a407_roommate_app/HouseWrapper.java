package com.tannerowens.a407_roommate_app;

import android.app.Application;

/**
 * Created by Nick on 4/25/2017.
 */

public class HouseWrapper extends Application {
    private static House house = null;

    protected HouseWrapper() {
        //empty
    }

    public static House getHouse() {
        if(house == null)
            return null;
        else return house;
    }

    public static void setHouse(House h) {
        house = h;
    }

}
