package com.breakout.myapplication.model;

import java.io.Serializable;

public class song_text  implements Serializable {

    public final static String TYPE_UNKNOWN = "unknown";

    public int eventID;
    public String name;
    public String text;

    public song_text (int eventID) {
        this.name = name;
        this.text = text;
        this.eventID = eventID;
    }

}