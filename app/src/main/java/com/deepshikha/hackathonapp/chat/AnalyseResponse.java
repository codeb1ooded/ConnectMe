package com.deepshikha.hackathonapp.chat;

import com.google.api.services.language.v1beta2.model.Entity;

import java.util.ArrayList;

/**
 * Created by megha on 12/11/17.
 */

public class AnalyseResponse {

    public ArrayList<Entity> entities;
    public String language;

}
