package com.example.library_management_system.modles;

import java.util.Date;

public class PatronModel extends UserModel{

    public PatronModel(int id, String username, String email, Date created_at, Date updated_at) {
        super(id, username, email, created_at, updated_at);
    }
}
