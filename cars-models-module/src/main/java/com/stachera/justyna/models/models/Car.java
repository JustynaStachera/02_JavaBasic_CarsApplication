package com.stachera.justyna.models.models;

import com.stachera.justyna.exceptions.CustomException;
import com.stachera.justyna.exceptions.ExceptionCode;
import com.stachera.justyna.models.converters.CarsJsonConverter;

import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * Developed by Justyna Stachera on 01.02.2019.
 * Package name: com.stachera.justyna.models
 * Last modified 19:49.
 * Copyright (c) 2019. All rights reserved.
 */
public class Car
{
    private String model;
    private BigDecimal price;
    private Integer mileage;
    private Engine engine;
    private CarBody carBody;
    private Wheel wheel;

    public Car()
    {
    }

    public Car(final String filePath)
    {
        prepareFields(filePath);
    }

    public String getModel()
    {
        return model;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public Integer getMileage()
    {
        return mileage;
    }

    public Engine getEngine()
    {
        return engine;
    }

    public CarBody getCarBody()
    {
        return carBody;
    }

    public Wheel getWheel()
    {
        return wheel;
    }

    void setModel(String model)
    {
        if (model != null && model.matches("^[A-Z\\s]+$"))
        {
            this.model = model;
        } else
        {
            try
            {
                throw new CustomException(ExceptionCode.CODE_250, "CAR: MODEL VALIDATION");
            } catch (CustomException e)
            {
                System.out.println(e.getExceptionInfo());
                System.out.println("CAR: NO MODEL ASSIGNED");
            }
        }
    }

    void setPrice(BigDecimal price)
    {
        if (price.compareTo(new BigDecimal(0)) > 0)
        {
            this.price = price;
        } else
        {
            try
            {
                throw new CustomException(ExceptionCode.CODE_250, "CAR: PRICE VALIDATION");
            } catch (CustomException e)
            {
                System.out.println(e.getExceptionInfo());
                System.out.println("CAR: NO PRICE ASSIGNED");

                this.price = new BigDecimal(0);
            }
        }
    }

    void setMileage(Integer mileage)
    {
        if (mileage > 0)
        {
            this.mileage = mileage;
        } else
        {
            try
            {
                throw new CustomException(ExceptionCode.CODE_250, "CAR: MILEAGE VALIDATION");
            } catch (CustomException e)
            {
                System.out.println(e.getExceptionInfo());
                System.out.println("CAR: NO MILEAGE ASSIGNED");

                this.mileage = 0;
            }
        }
    }

    void setEngine(Engine engine)
    {
        this.engine = engine;
    }

    void setCarBody(CarBody carBody)
    {
        this.carBody = carBody;
    }

    void setWheel(Wheel wheel)
    {
        this.wheel = wheel;
    }

    @Override
    public String toString()
    {
        return "Car{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", engine=" + engine +
                ", carBody=" + carBody +
                ", wheel=" + wheel +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) &&
                Objects.equals(price, car.price) &&
                Objects.equals(mileage, car.mileage) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(carBody, car.carBody) &&
                Objects.equals(wheel, car.wheel);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(model, price, mileage, engine, carBody, wheel);
    }

    private void prepareFields(final String filePath)
    {
        Car car = new CarsJsonConverter(
                new File("")
                        .getAbsolutePath()
                        .concat(filePath.replaceAll("/", Matcher.quoteReplacement(File.separator))))
                .convert();

        setMileage(car.getMileage());
        setEngine(car.getEngine());
        setCarBody(car.getCarBody());
        setModel(car.getModel());
        setPrice(car.getPrice());
        setWheel(car.getWheel());
    }
}
