package com.andersen.coworking.services;

import com.andersen.coworking.utils.FileManager;

public class FileManagerInitializer {
    public FileManager initializeFileManager(String filePath) {
        return new FileManager(filePath);
    }
}
