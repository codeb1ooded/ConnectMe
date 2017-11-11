package com.deepshikha.hackathonapp;

import java.util.List;

/**
 * Created by deepshikha
 */

public interface Callbacks {
    public interface ListEventCallback {
        void listEvents(List<String> list);
    }

    public interface RecommendationCallback {
        void recommendations(List<String> list);
    }

    public interface RegistrationCallback {
        void registrationStatus(boolean status);
    }


}
