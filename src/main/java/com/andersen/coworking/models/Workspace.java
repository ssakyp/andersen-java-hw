package com.andersen.coworking.models;

import java.util.Objects;

public class Workspace {
    private final int id;
    private final double price;
    private boolean isAvailable;
    // Using enum for type
    private final WorkspaceType type;

    // Constructor
    public Workspace(int id, double price, boolean isAvailable, WorkspaceType type) {
        this.id = id;
        this.price = price;
        this.isAvailable = isAvailable;
        this.type = type;
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public WorkspaceType getType() {
        return type;
    }
    public double getPrice() {
        return price;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        // Convert WorkspaceType enum to a human-readable string
        String typeString = type.toString().replaceAll("_", " ");

        // Capitalize the first letter of the type
        typeString = typeString.substring(0, 1).toUpperCase() + typeString.substring(1);

        // Format the availability as a readable string
        String availability = isAvailable ? "Available" : "Unavailable";

        return id + ", " + type + ", " + price + "$, " + availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return id == workspace.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
