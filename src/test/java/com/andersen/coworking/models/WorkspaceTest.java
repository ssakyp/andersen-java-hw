package com.andersen.coworking.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkspaceTest {
    @Test
    void testWorkspaceCreation() {
        Workspace workspace = new Workspace(1, 10.0, true, WorkspaceType.OPEN_SPACE);
        assertEquals(1, workspace.getId());
        assertTrue(workspace.isAvailable());
        assertEquals(WorkspaceType.OPEN_SPACE, workspace.getType());
        assertEquals(10.0, workspace.getPrice());
    }

    @Test
    void testWorkspaceSetAvailable() {
        Workspace workspace = new Workspace(2, 11.1, false, WorkspaceType.CONFERENCE_ROOM);
        assertFalse(workspace.isAvailable());

        workspace.setAvailable(true);
        assertTrue(workspace.isAvailable());
    }

    @Test
    void testWorkspaceToString() {
        Workspace workspace = new Workspace(2, 11.1, false, WorkspaceType.CONFERENCE_ROOM);
        String expectedWorkspaceString = workspace.getId() + ", " + workspace.getType() + ", " + workspace.getPrice() + "$, Unavailable";
        assertEquals(expectedWorkspaceString, workspace.toString());
    }

    @Test
    void testWorkspaceEqualsAndHashCode() {
        Workspace workspace = new Workspace(2, 11.1, false, WorkspaceType.CONFERENCE_ROOM);
        Workspace workspace2 = new Workspace(2, 11.1, false, WorkspaceType.CONFERENCE_ROOM);
        Workspace workspace3 = new Workspace(3, 11.1, true, WorkspaceType.PHONE_BOOTH);

        assertEquals(workspace, workspace2);
        assertNotEquals(workspace, workspace3);

        assertEquals(workspace.hashCode(), workspace2.hashCode());
        assertNotEquals(workspace.hashCode(), workspace3.hashCode());
    }
}
