package com.stachera.justyna.models.models;

import com.stachera.justyna.exceptions.CustomException;
import com.stachera.justyna.exceptions.ExceptionCode;
import com.stachera.justyna.models.enums.WheelType;

import java.util.Objects;

/**
 * Developed by Justyna Stachera on 01.02.2019.
 * Package name: com.stachera.justyna.enums
 * Last modified 19:27.
 * Copyright (c) 2019. All rights reserved.
 */
public class Wheel
{
    private String model;
    private Integer size;
    private WheelType type;

    public Wheel()
    {
    }

    private Wheel(WheelBuilder wheelBuilder)
    {
        this.model = wheelBuilder.model;
        this.size = wheelBuilder.size;
        this.type = wheelBuilder.type;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public WheelType getType()
    {
        return type;
    }

    public void setType(WheelType type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "Wheel{" +
                "model='" + model + '\'' +
                ", size=" + size +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return Objects.equals(model, wheel.model) &&
                Objects.equals(size, wheel.size) &&
                type == wheel.type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(model, size, type);
    }

    public static class WheelBuilder
    {
        private String model;
        private Integer size;
        private WheelType type;

        public WheelBuilder model(String model)
        {
            if (model != null && model.matches("^[A-Z\\s]+$"))
            {
                this.model = model;
            } else
            {
                try
                {
                    throw new CustomException(ExceptionCode.CODE_250, "WHEEL: MODEL VALIDATION");
                } catch (CustomException e)
                {
                    System.out.println(e.getExceptionInfo());
                    System.out.println("WHEEL: NO MODEL ASSIGNED");

                    this.model = "DEFAULT";
                }
            }
            return this;
        }

        public WheelBuilder size(Integer size)
        {
            if (size > 0)
            {
                this.size = size;
            } else
            {
                try
                {
                    throw new CustomException(ExceptionCode.CODE_250, "WHEEL: SIZE VALIDATION");
                } catch (CustomException e)
                {
                    System.out.println(e.getExceptionInfo());
                    System.out.println("WHEEL: NO SIZE ASSIGNED");
                }
            }

            return this;
        }

        public WheelBuilder type(WheelType type)
        {
            this.type = type;

            return this;
        }

        public Wheel build(WheelBuilder wheelBuilder)
        {
            return new Wheel(wheelBuilder);
        }
    }

}
