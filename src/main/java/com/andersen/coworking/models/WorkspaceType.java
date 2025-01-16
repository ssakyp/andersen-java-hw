package com.andersen.coworking.models;

public enum WorkspaceType {
    OPEN_SPACE,
    PRIVATE_ROOM,
    MEETING_ROOM,
    PHONE_BOOTH,
    CONFERENCE_ROOM;

    public static WorkspaceType fromString(String type) {
        switch (type.toLowerCase()) {
            case "open space": return OPEN_SPACE;
            case "private room": return PRIVATE_ROOM;
            case "meeting room": return MEETING_ROOM;
            case "phone booth": return PHONE_BOOTH;
            case "conference room": return CONFERENCE_ROOM;
            default: throw new IllegalArgumentException("Unknown workspace type: " + type);
        }
    }

    @Override
    public String toString() {
        String formatted = name().replace("_", " ").toLowerCase();
        return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
    }
}
