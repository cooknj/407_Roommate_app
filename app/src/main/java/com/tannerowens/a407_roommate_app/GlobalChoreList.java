package com.tannerowens.a407_roommate_app;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nick on 3/30/2017.
 */

public class GlobalChoreList extends Application {
    //Key - String assignedTo
    //Value - ArrayList<String> listOfChores
    private static HashMap<String, ArrayList<String>> choreMap = null;

    protected GlobalChoreList() {
        //empty
    }

    public static HashMap<String, ArrayList<String>> getChoreMap() {
        if(choreMap == null) {
            choreMap = new HashMap<String, ArrayList<String>>();
        }
        return choreMap;
    }

}
