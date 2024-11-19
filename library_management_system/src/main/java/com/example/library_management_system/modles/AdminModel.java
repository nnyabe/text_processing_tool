package com.example.library_management_system.modles;

import java.util.Date;

/**
 * Represents an administrator user in the library management system.
 * This class extends the UserModel to include additional properties such as role and password.
 */
public class AdminModel extends UserModel {

    private Enums.Roles role;
    private String password;

    /**
     * Constructs an AdminModel object with the specified values.
     *
     * @param id the unique identifier of the admin
     * @param username the username of the admin
     * @param email the email address of the admin
     * @param created_at the date and time when the admin account was created
     * @param updated_at the date and time when the admin account was last updated
     * @param role the role of the admin (e.g., SUPER_ADMIN, ADMIN)
     * @param password the password associated with the admin account
     */
    public AdminModel(int id, String username, String email, Date created_at, Date updated_at,
                      Enums.Roles role, String password) {
        super(id, username, email, created_at, updated_at);
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the password of the admin.
     *
     * @return the password of the admin
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the admin.
     *
     * @param password the new password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the role of the admin.
     *
     * @return the role of the admin (e.g., SUPER_ADMIN, ADMIN)
     */
    public Enums.Roles getRole() {
        return role;
    }

    /**
     * Sets the role for the admin.
     *
     * @param role the new role to be set (e.g., SUPER_ADMIN, ADMIN)
     */
    public void setRole(Enums.Roles role) {
        this.role = role;
    }
}
