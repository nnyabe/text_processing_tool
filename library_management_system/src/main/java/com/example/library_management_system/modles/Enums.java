package com.example.library_management_system.modles;

public class Enums {

    /**
     * User roles in the system.
     */
    public enum Roles {
        LIBRARIAN, ADMIN, MANAGER;
    }

    /**
     * Status of a transaction.
     */
    public enum Stautus {
        PENDING, APPROVED, RETURNED;
    }

    /**
     * Types of transactions.
     */
    public enum Types {
        BORROW, RESERVATION;
    }
}
