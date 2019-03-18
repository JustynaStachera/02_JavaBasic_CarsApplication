package com.stachera.justyna.services;

import com.stachera.justyna.models.enums.*;
import com.stachera.justyna.models.models.Car;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Developed by Justyna Stachera on 07.02.2019.
 * Package name: com.stachera.justyna.services
 * Last modified 18:59.
 * Copyright (c) 2019. All rights reserved.
 */
class CarServiceImpl
{
    private final Set<Car> cars;

    CarServiceImpl(Set<Car> cars)
    {
        this.cars = cars;
    }

    /**
     * It returns {@link Car} collection sorted by {@link Criterion} and ascending
     * or descending order.
     *
     * @param criterion {@link Criterion}
     * @param isDesc ascending/descending sorting order
     * @return {@link Car} collection
     */
    List<Car> getCarsFilteredByCarCriterion(Criterion criterion, boolean isDesc)
    {
        List<Car> sortedCars = new ArrayList<>();

        switch (criterion)
        {
            case COMPONENTS_NUMBER:
                sortedCars = cars
                        .stream()
                        .sorted((o1, o2) -> isDesc ? Integer.compare(o2.getCarBody().getComponents().size(), o1.getCarBody().getComponents().size()) : Integer.compare(o1.getCarBody().getComponents().size(), o2.getCarBody().getComponents().size()))
                        .collect(Collectors.toList());
                break;
            case POWER:
                sortedCars = cars
                        .stream()
                        .sorted((o1, o2) -> isDesc ? o2.getEngine().getPower().compareTo(o1.getEngine().getPower()) : o1.getEngine().getPower().compareTo(o2.getEngine().getPower()))
                        .collect(Collectors.toList());
                break;
            case WHEEL_SIZE:
                sortedCars = cars
                        .stream()
                        .sorted((o1, o2) -> isDesc ? o2.getWheel().getSize().compareTo(o1.getWheel().getSize()) : o1.getWheel().getSize().compareTo(o2.getWheel().getSize()))
                        .collect(Collectors.toList());
            default:
                break;
        }

        return sortedCars;
    }

    /**
     * It return {@link Car} collection by {@link CarBodyType} and price between priceFrom and priceTo.
     * @param type {@link CarBodyType}
     * @param priceFrom priceFrom
     * @param priceTo priceTo
     * @return {@link Car} collection
     */
    List<Car> getCarsByCarBodyTypeAndPriceBetween(CarBodyType type, BigDecimal priceFrom, BigDecimal priceTo)
    {
        return cars
                .stream()
                .filter(p -> p.getCarBody().getType().equals(type) && p.getPrice().compareTo(priceFrom) >= 0 && p.getPrice().compareTo(priceTo) <= 0)
                .collect(Collectors.toList());
    }

    /**
     * It returns sorted alphabetically {@link Car} collection per {@link EngineType}.
     *
     * @param type {@link EngineType}
     * @return {@link Car} collection
     */
    List<Car> getCarsByEngineType(EngineType type)
    {
        return cars
                .stream()
                .filter(p -> p.getEngine().getType().equals(type))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

    /**
     * It returns statistics by {@link Value}.
     *
     * @param value {@link Value}
     * @return statistics per {@link Value}
     */
    String getStatisticsByValue(Value value)
    {
        String results = "";

        switch (value)
        {
            case POWER:
                BigDecimal powerMin = cars
                        .stream()
                        .min(Comparator.comparing(m -> m.getEngine().getPower())).map(m -> m.getEngine().getPower())
                        .orElse(new BigDecimal(0));
                BigDecimal powerMax = cars
                        .stream()
                        .max(Comparator.comparing(m -> m.getEngine().getPower())).map(m -> m.getEngine().getPower())
                        .orElse(new BigDecimal(0));
                BigDecimal powerAvg = cars.stream().map(m -> m.getEngine().getPower()).reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(new BigDecimal(cars.size()), RoundingMode.HALF_UP);

                results = MessageFormat
                        .format("PowerStatistics[min={0}, max={1}, average={2}]", powerMin, powerMax, powerAvg);
                break;
            case MILEAGE:
                IntSummaryStatistics mileageStatistics = cars.stream().mapToInt(Car::getMileage).summaryStatistics();

                results = MessageFormat
                        .format("MileageStatistics[min={0}, max={1}, average={2}]", mileageStatistics.getMin(),
                                mileageStatistics.getMin(), mileageStatistics.getAverage());
                break;
            case PRICE:
                BigDecimal priceMin = cars.stream().min(Comparator.comparing(Car::getPrice)).map(Car::getPrice)
                        .orElse(new BigDecimal(0));
                BigDecimal priceMax = cars.stream().min(Comparator.comparing(Car::getPrice)).map(Car::getPrice)
                        .orElse(new BigDecimal(0));
                BigDecimal priceAvg = cars.stream().map(Car::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(new BigDecimal(cars.size()), RoundingMode.HALF_UP);

                results = MessageFormat
                        .format("PriceStatistics[min={0}, max={1}, average={2}]", priceMin, priceMax, priceAvg);
                break;
            default:
                break;
        }

        return results;
    }

    /**
     * It returns a map that contains {@link Car} as key and {@link Car} mileage
     * as value. The map is sorted descending by value.
     *
     * @return {@link Car} - {@link Car} mileage map
     */
    Map<Car, Integer> getMapCarByMileage()
    {
        return cars
                .stream()
                .collect(Collectors.toMap(obj -> obj, Car::getMileage))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    /**
     * It returns a {@link WheelType} - {@link Car} collection size map.
     * The map is sorted descending by collection size.
     *
     * @return {@link WheelType} - {@link Car} collection size map
     */
    Map<WheelType, Integer> getMapCarByWheelType()
    {
        return new HashSet<>(Arrays.asList(WheelType.values()))
                .stream()
                .collect(Collectors.toMap(
                        type -> type,
                        type -> cars.stream().filter(p -> p.getWheel().getType().equals(type)).collect(Collectors.toSet()).size()
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    /**
     * It returns {@link Car} collection that contain all arguments
     * components. The collection is sorted alphabetically by {@link Car}
     * model.
     *
     * @param components {@link Car} components
     * @return {@link Car} collection
     */
    List<Car> getCarsByComponents(List<String> components)
    {
        return cars
                .stream()
                .filter(p -> p.getCarBody().getComponents().containsAll(components))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());

    }
}
