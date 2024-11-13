package com.example.library_management_system.modles;

import java.util.Date;


public class AdminModel extends UserModel{
    private Enums.Roles role;

    public AdminModel(int id, String username, String email, Date created_at, Date updated_at,
                      Enums.Roles role) {
        super(id, username, email, created_at, updated_at);

        this.role = role;
    }

    public Enums.Roles getRole() {
        return role;
    }

    public void setRole(Enums.Roles role) {
        this.role = role;
    }
}
