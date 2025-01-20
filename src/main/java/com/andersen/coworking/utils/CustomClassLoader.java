package com.andersen.coworking.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName) throws ClassNotFoundException {
        String path = fileName.replace('.', File.separatorChar) + ".class";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {

            try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
                int nextValue;
                while (true) {
                    assert inputStream != null;
                    if ((nextValue = inputStream.read()) == -1) break;
                    byteStream.write(nextValue);
                }
                return byteStream.toByteArray();
            } catch (IOException e) {
                throw new ClassNotFoundException("Error reading class file: " + fileName, e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}