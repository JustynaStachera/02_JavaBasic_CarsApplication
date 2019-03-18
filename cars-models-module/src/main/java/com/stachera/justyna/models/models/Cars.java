package com.stachera.justyna.models.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Developed by Justyna Stachera on 01.02.2019.
 * Package name: com.stachera.justyna.models
 * Last modified 19:58.
 * Copyright (c) 2019. All rights reserved.
 */
public class Cars
{
    private final Set<Car> cars = new HashSet<>();

    public Cars()
    {
    }

    public Cars(Collection<String> filePaths)
    {
        filePaths.forEach(filePath -> cars.add(new Car(filePath)));
    }

    public Set<Car> getCars()
    {
        return cars;
    }

    @Override
    public String toString()
    {
        return "Cars{" +
                "cars=" + cars +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars1 = (Cars) o;
        return Objects.equals(cars, cars1.cars);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(cars);
    }
}
