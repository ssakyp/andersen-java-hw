package com.andersen.coworking.main;


import com.andersen.coworking.roles.Admin;
import com.andersen.coworking.roles.Client;
import com.andersen.coworking.services.ClassLoaderService;
import com.andersen.coworking.services.FileManagerInitializer;
import com.andersen.coworking.services.MenuService;
import com.andersen.coworking.utils.FileManager;

public class Main {
    public static void main(String[] args) {
        ClassLoaderService classLoaderService = new ClassLoaderService();
        Class<?> dynamicClass = classLoaderService.loadAdminClass("com.andersen.coworking.roles.Admin");
        if (dynamicClass != null) {
            System.out.println("Class loaded: " + dynamicClass.getName());
        }

        FileManagerInitializer fileManagerInitializer = new FileManagerInitializer();
        FileManager workspaceFileManager = fileManagerInitializer.initializeFileManager("workspaceState");

        Admin admin = new Admin(workspaceFileManager);
        Client client = new Client(admin);

        MenuService menuService = new MenuService(admin, client);
        menuService.displayMainMenu();
    }
}
