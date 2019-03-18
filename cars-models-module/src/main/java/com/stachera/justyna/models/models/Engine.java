package com.stachera.justyna.models.models;

import com.stachera.justyna.exceptions.CustomException;
import com.stachera.justyna.exceptions.ExceptionCode;
import com.stachera.justyna.models.enums.EngineType;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Developed by Justyna Stachera on 01.02.2019.
 * Package name: com.stachera.justyna.models
 * Last modified 19:25.
 * Copyright (c) 2019. All rights reserved.
 */
public class Engine
{
    private EngineType type;
    private BigDecimal power;

    public Engine()
    {
    }

    private Engine(EngineBuilder builder)
    {
        this.type = builder.type;
        this.power = builder.power;
    }

    public EngineType getType()
    {
        return type;
    }

    public void setType(EngineType type)
    {
        this.type = type;
    }

    public BigDecimal getPower()
    {
        return power;
    }

    public void setPower(BigDecimal power)
    {
        this.power = power;
    }

    @Override
    public String toString()
    {
        return "Engine{" +
                "type=" + type +
                ", power=" + power +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return type == engine.type &&
                Objects.equals(power, engine.power);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(type, power);
    }

    public static class EngineBuilder
    {
        private BigDecimal power;
        private EngineType type;

        public EngineBuilder power(BigDecimal power)
        {
            if (power.compareTo(new BigDecimal(0)) > 0)
            {
                this.power = power;
            } else
            {
                try
                {
                    throw new CustomException(ExceptionCode.CODE_250, "ENGINE: POWER VALIDATION");
                } catch (CustomException e)
                {
                    System.out.println(e.getExceptionInfo());
                    System.out.println("ENGINE: NO POWER ASSIGNED");

                    this.power = new BigDecimal(0);
                }
            }

            return this;
        }

        public EngineBuilder type(EngineType type)
        {
            this.type = type;

            return this;
        }

        public Engine build()
        {
            return new Engine(this);
        }
    }

}
