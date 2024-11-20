package com.example.library_management_system.modles;

import java.util.Date;

public class PatronModel extends UserModel{

    // Default constructor
    public PatronModel(){

    }

    /**
     * Constructor for creating a new patron without ID and timestamps.
     */
    public PatronModel(String username, String email){
        this(0, username, email, null, null);
    }

    /**
     * Full constructor for the patron model with ID and timestamps.
     */
    public PatronModel(int id, String username,
                       String email, Date created_at,
                       Date updated_at) {
        super(id, username, email, created_at, updated_at);
    }
}
