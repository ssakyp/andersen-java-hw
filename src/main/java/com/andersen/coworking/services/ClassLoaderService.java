package com.andersen.coworking.services;

import com.andersen.coworking.utils.CustomClassLoader;

public class ClassLoaderService {
    public Class<?> loadAdminClass(String className) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            return customClassLoader.loadClass(className);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
