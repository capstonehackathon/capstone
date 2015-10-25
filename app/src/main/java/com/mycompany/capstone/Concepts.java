package com.mycompany.capstone;

import java.io.Serializable;

/**
 * Created by sjayaram on 10/24/2015.
 */
public class Concepts implements Serializable{
    String tag;
    String weight;

    public Concepts(String tag, String weight)
    {
        this.tag = tag;
        this.weight = weight;
    }
}
