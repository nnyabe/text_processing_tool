package com.example.library_management_system.modles;

public abstract class ResourcesModel {
    private int id;
    private boolean availalbeState;
    private String title;
    private String publisher;
    private int totalCopies;
    private int copiesLeft;

    public ResourcesModel() {
    }

    public ResourcesModel(int id, boolean availalbeState, String title, String publisher, int totalCopies,
                          int copiesLeft) {

        this.id = id;
        this.availalbeState = availalbeState;
        this.title = title;
        this.publisher = publisher;
        this.totalCopies = totalCopies;
        this.copiesLeft = copiesLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailalbeState() {
        return availalbeState;
    }

    public void setAvailalbeState(boolean availalbeState) {
        this.availalbeState = availalbeState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getCopiesLeft() {
        return copiesLeft;
    }

    public void setCopiesLeft(int copiesLeft) {
        this.copiesLeft = copiesLeft;
    }

}
