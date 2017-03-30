package com.tannerowens.a407_roommate_app;

import android.app.Application;
import java.util.ArrayList;

/**
 * Created by Nick on 3/30/2017.
 */

public class GlobalChoreList extends Application {
    private static ArrayList<String> choreList;

    public ArrayList<String> getChoreList() {
        return this.choreList;
    }

    public void addChoreToList(String chore) {
        choreList.add(chore);
    }

}
