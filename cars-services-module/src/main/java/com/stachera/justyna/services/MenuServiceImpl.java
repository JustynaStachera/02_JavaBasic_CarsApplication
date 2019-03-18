package com.stachera.justyna.services;

import com.stachera.justyna.models.enums.CarBodyType;
import com.stachera.justyna.models.enums.Criterion;
import com.stachera.justyna.models.enums.EngineType;
import com.stachera.justyna.models.enums.Value;
import com.stachera.justyna.models.models.Car;
import com.stachera.justyna.models.models.Cars;

import java.math.BigDecimal;
import java.util.*;

/**
 * Developed by Justyna Stachera on 07.02.2019.
 * Package name: com.stachera.justyna.services
 * Last modified 19:00.
 * Copyright (c) 2019. All rights reserved.
 */
public class MenuServiceImpl
{
    private CarServiceImpl carService;

    public static void run(final List<String> filePaths)
    {
        new MenuServiceImpl(filePaths);
    }

    private MenuServiceImpl(final List<String> filePaths)
    {
        carService = new CarServiceImpl(new Cars(filePaths).getCars());

        runMenu();
    }

    private void runMenu()
    {
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            showMenu();

            String text = sc.nextLine();

            System.out.println();

            switch (text.toLowerCase())
            {
                case "1":
                    showData(getCarsFilteredByCarCriterion(sc));
                    break;
                case "2":
                    showData(getCarsByCarBodyTypeAndPriceBetween(sc));
                    break;
                case "3":
                    showData(getCarsByEngineType(sc));
                    break;
                case "4":
                    showData(getStatisticsByValue(sc));
                    break;
                case "5":
                    showData(carService.getMapCarByMileage());
                    break;
                case "6":
                    showData(carService.getMapCarByWheelType());
                    break;
                case "7":
                    showData(getCarsComponents(sc));
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Wrong option - choose again!");
            }

            System.out.println();
        }
    }

    private List<Car> getCarsComponents(Scanner sc)
    {
        List<Car> cars = new ArrayList<>();

        try
        {
            System.out.print("Insert components separate with comma: ");
            List<String> components = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));
            System.out.println();

            cars.addAll(carService.getCarsByComponents(components));

        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }

        return cars;
    }

    private String getStatisticsByValue(Scanner sc)
    {
        String results = "";
        try
        {
            System.out.println("Choose below criterion: ");
            Arrays.asList(Value.values()).forEach(System.out::println);
            System.out.print("Insert: ");
            Value value = Value.valueOf(sc.nextLine().toUpperCase());
            System.out.println();

            results = carService.getStatisticsByValue(value);
        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }

        return results;
    }

    private List<Car> getCarsByEngineType(Scanner sc)
    {
        List<Car> cars = new ArrayList<>();

        try
        {
            System.out.println("Choose below criterion: ");
            Arrays.asList(EngineType.values()).forEach(System.out::println);
            System.out.print("Insert: ");
            EngineType engineType = EngineType.valueOf(sc.nextLine().toUpperCase());
            System.out.println();

            cars.addAll(carService.getCarsByEngineType(engineType));
        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }

        return cars;
    }

    private List<Car> getCarsByCarBodyTypeAndPriceBetween(Scanner sc)
    {
        List<Car> cars = new ArrayList<>();

        try {
            System.out.println("Choose below criterion: ");
            Arrays.asList(CarBodyType.values()).forEach(System.out::println);
            System.out.print("Insert: ");
            CarBodyType carBodyType = CarBodyType.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Insert lower price: ");
            BigDecimal lowerPrice = new BigDecimal(sc.nextLine());

            System.out.print("Insert upper price: ");
            BigDecimal upperPrice = new BigDecimal(sc.nextLine());
            System.out.println();

            cars.addAll(carService.getCarsByCarBodyTypeAndPriceBetween(carBodyType, lowerPrice, upperPrice));
        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }

        return  cars;
    }

    private List<Car> getCarsFilteredByCarCriterion(Scanner sc)
    {
        List<Car> cars = new ArrayList<>();

        try {
            System.out.println("Choose below criterion: ");
            Arrays.asList(Criterion.values()).forEach(System.out::println);
            System.out.print("Insert: ");
            Criterion criterion = Criterion.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Do you want descending sort (Y/N)? ");
            boolean isDesc = sc.nextLine().equalsIgnoreCase("Y");
            System.out.println();

            cars.addAll(carService.getCarsFilteredByCarCriterion(criterion, isDesc));
        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }

        return cars;
    }

    private void showData(String string)
    {
        System.out.println(string);
    }

    private void showData(Collection<?> set)
    {
        set.forEach(System.out::println);
    }

    private void showData(Map<?, ?> map)
    {
        map.forEach((key, value) -> System.out.println(key + " " + value));
    }

    private void showMenu()
    {
        System.out.println("1) Show cars filtered by criterion");
        System.out.println("2) Show cars by car body type and price between...");
        System.out.println("3) Show cars by engine type");
        System.out.println("4) Show statistics by value");
        System.out.println("5) Show car map by mileage");
        System.out.println("6) Show cars map by wheel type");
        System.out.println("7) Show cars by components");
        System.out.println("Press X to quit");
        System.out.print("Insert: ");
    }
}
