package com.stachera.justyna.models.models;

import com.stachera.justyna.exceptions.CustomException;
import com.stachera.justyna.exceptions.ExceptionCode;
import com.stachera.justyna.models.enums.CarBodyColor;
import com.stachera.justyna.models.enums.CarBodyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Developed by Justyna Stachera on 01.02.2019.
 * Package name: com.stachera.justyna.models
 * Last modified 19:43.
 * Copyright (c) 2019. All rights reserved.
 */
public class CarBody
{
    private CarBodyColor color;
    private CarBodyType type;
    private List<String> components;

    public CarBody()
    {
    }

    private CarBody(CarBodyBuilder carBodyBuilder)
    {
        this.color = carBodyBuilder.color;
        this.type = carBodyBuilder.type;
        this.components = carBodyBuilder.components;
    }

    public CarBodyColor getColor()
    {
        return color;
    }

    public void setColor(CarBodyColor color)
    {
        this.color = color;
    }

    public CarBodyType getType()
    {
        return type;
    }

    public void setType(CarBodyType type)
    {
        this.type = type;
    }

    public List<String> getComponents()
    {
        return components;
    }

    public void setComponents(List<String> components)
    {
        this.components = components;
    }

    public static class CarBodyBuilder
    {
        private CarBodyColor color;
        private CarBodyType type;
        private List<String> components;

        public CarBodyBuilder color(CarBodyColor color)
        {
            this.color = color;

            return this;
        }

        public CarBodyBuilder type(CarBodyType type)
        {
            this.type = type;

            return this;
        }

        @Override
        public String toString()
        {
            return "CarBodyBuilder{" +
                    "color=" + color +
                    ", type=" + type +
                    ", components=" + components +
                    '}';
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CarBodyBuilder that = (CarBodyBuilder) o;
            return color == that.color &&
                    type == that.type &&
                    Objects.equals(components, that.components);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(color, type, components);
        }

        public CarBodyBuilder components(List<String> components)
        {
            if (components.stream().allMatch(p -> p.matches("^[A-Z\\s]+$")))
            {
                this.components = components;
            } else
            {
                try
                {
                    throw new CustomException(ExceptionCode.CODE_250, "CAR_BODY: COMPONENTS VALIDATION");
                } catch (CustomException e)
                {
                    System.out.println(e.getExceptionInfo());
                    System.out.println("CAR_BODY: NO COMPONENTS ASSIGNED");

                    this.components = new ArrayList<>();
                }
            }

            return this;
        }

        public CarBody build()
        {
            return new CarBody(this);
        }
    }
}
