/**
 * Developed by Justyna Stachera on 25.02.2019.
 * Package name:
 * Last modified 19:58.
 * Copyright (c) 2019. All rights reserved.
 */module cars.models.module {
     requires cars.exceptions.module;
     requires gson.module;

     exports com.stachera.justyna.models.models;
     exports com.stachera.justyna.models.enums;

     opens com.stachera.justyna.models.models;
}
