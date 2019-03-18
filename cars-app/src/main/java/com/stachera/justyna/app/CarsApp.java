package com.stachera.justyna.app;

import com.stachera.justyna.services.MenuServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Developed by Justyna Stachera on 01.02.2019.
 * Package name: com.stachera.justyna.models
 * Last modified 19:49.
 * Copyright (c) 2019. All rights reserved.
 */
public class CarsApp
{
    private static final String BASE_FILE_PATH = "/cars-app/src/main/resources/";

    private static List<String> prepareFilePaths()
    {
        List<String> filePaths = new ArrayList<>();

        filePaths.add(BASE_FILE_PATH + "car01.json");
        filePaths.add(BASE_FILE_PATH + "car02.json");
        filePaths.add(BASE_FILE_PATH + "car03.json");

        return filePaths;
    }

    public static void main(String[] args)
    {
        MenuServiceImpl.run(prepareFilePaths());
    }
}
