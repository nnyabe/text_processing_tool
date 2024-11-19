package com.example.library_management_system.modles;

import java.util.Date;

public abstract class ResourcesModel {
    private int id;
    private boolean availableState;
    private String title;
    private String publisher;
    private int totalCopies;
    private int copiesLeft;
    private Date createdAt;
    private Date updatedAt;

    /**
     * Constructor without ID, with resource type (used for initialization).
     */
    public ResourcesModel(boolean availableState, String title, String publisher, int totalCopies,
                          int copiesLeft, String resourceType) {
        this(0, availableState, title, publisher, totalCopies, copiesLeft, new Date(), new Date());
    }

    /**
     * Full constructor with ID and timestamps.
     */
    public ResourcesModel(int id, boolean availableState, String title, String publisher, int totalCopies,
                          int copiesLeft, Date createdAt, Date updatedAt) {
        this.id = id;
        this.availableState = availableState;
        this.title = title;
        this.publisher = publisher;
        this.totalCopies = totalCopies;
        this.copiesLeft = copiesLeft;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailableState() {
        return availableState;
    }

    public void setAvailableState(boolean availableState) {
        this.availableState = availableState;
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

    /**
     * String representation of the resource model.
     */
    @Override
    public String toString(){
        return "\n{ id = "+ id +
                ", available_state = " + availableState +
                ", title = " + title +
                ", publisher = " + publisher +
                ", total_copies = " + totalCopies +
                ", copies_left = " + copiesLeft +
                ", created_at = " + createdAt +
                ", updated_at = " + updatedAt;
    }
}
