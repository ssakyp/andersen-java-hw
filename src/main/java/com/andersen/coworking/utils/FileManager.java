package com.andersen.coworking.utils;

import com.andersen.coworking.models.Workspace;
import com.andersen.coworking.models.WorkspaceType;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    private File getFileFromResources() throws FileNotFoundException {
        URL resourceURL = getClass().getClassLoader().getResource(filePath);
        if (resourceURL == null) {
            throw new FileNotFoundException("File not found in resources: " + filePath);
        }
        return new File(resourceURL.getFile());
    }

    // Read from a file
    public <T> Set<T> readItems (Class<T> clazz) {
        Set<T> itemSet = new HashSet<T>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getFileFromResources()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if(clazz == Workspace.class) {
                    int id = Integer.parseInt(fields[0]);
                    String workspaceTypeString = fields[1];
                    double price = Double.parseDouble(fields[2] );
                    boolean isAvailable = Boolean.parseBoolean(fields[3]);

                    WorkspaceType type = WorkspaceType.fromString(workspaceTypeString);
                    itemSet.add(clazz.cast(new Workspace(id, price, isAvailable, type)));
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading file from the path: " + filePath);
        }
        return itemSet;
    }

    // Writing to a file
    public <T> void writeItems (Set<T> itemSet, Class<T> itemType) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFileFromResources()))) {
            itemSet.forEach(item -> {
                if(itemType == Workspace.class) {
                    Workspace workspace = (Workspace) item;
                    String line = String.format("%d, %s, %.2f, %b",
                            workspace.getId(),
                            workspace.getType(),
                            workspace.getPrice(),
                            workspace.isAvailable());
                    try {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
            });
            String itemName = itemType.getSimpleName();
            System.out.println("Successfully saved " + itemName + "s into the path: " + filePath);
        } catch (IOException e) {
            System.out.println("Error while writing file into the path: " + filePath + ": " + e.getMessage());
        }
    }
}
