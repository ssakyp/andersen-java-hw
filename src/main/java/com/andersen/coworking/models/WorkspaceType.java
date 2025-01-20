package com.andersen.coworking.models;

import java.util.Locale;

public enum WorkspaceType {
    OPEN_SPACE,
    PRIVATE_ROOM,
    MEETING_ROOM,
    PHONE_BOOTH,
    CONFERENCE_ROOM;

    public static WorkspaceType fromString(String type) {
        return switch (type.toLowerCase().trim()) {
            case "open space" -> OPEN_SPACE;
            case "private room" -> PRIVATE_ROOM;
            case "meeting room" -> MEETING_ROOM;
            case "phone booth" -> PHONE_BOOTH;
            case "conference room" -> CONFERENCE_ROOM;
            default -> throw new IllegalArgumentException("Unknown workspace type: " + type);
        };
    }

    @Override
    public String toString() {
        String formatted = name().replace("_", " ").toLowerCase();
        return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
    }
}
