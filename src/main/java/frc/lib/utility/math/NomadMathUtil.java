/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.utility.math;

/**
 * Math utility functions not included in WPILib or the Java Math library.
 */
public final class NomadMathUtil {
    /**
     * Linear interpolation from a given range to another given range
     * @param value The value to interpolate.
     * @param min1 The lower bound of the input range.
     * @param max1 The upper bound of the input range.
     * @param min2 The lower bound of the output range.
     * @param max2 The upper bound of the output range.
     * @return The interpolated value.
     */
    public static double lerp(double value, double min1, double max1, double min2, double max2){
        double percent = (value-min1) / (max1 - min1); //calculates where in the input range the value is.
        double output = min2 + (percent* (max2-min2));
        return output;
    }
    /**
     * Linear interpolation from a percentage to a range.
     * @param <T> The type of Number you want to lerp
     * @param min The lower bound of the output range
     * @param max The upper bound of the output range
     * @param percent The percentage, where 0 is the lower bound and 1 is the upper bound
     * @return The interpolated value.
     */
    public static <T extends Number> double lerp(T min, T max, double percent){
        return min.doubleValue() + (percent * (max.doubleValue() - min.doubleValue()));
    }

    /**
     * Calculates the percentage at which the value lies between <b>a</b> and <b>b</b>. <i>Please</i> make sure that <b>a</b> and <b>b</b> are different.
     * @param a The lower bound
     * @param b The upper bound
     * @param value The value between <b>a</b> and <b>b</b> to check
     * @return The percentage at which <b>value</b> is between <b>a</b> and <b>b</b>
     */
    public static double inverseLerp(double a, double b, double value){
        return (value - a) / (b - a);
    }

    /**
     * Clamps the <b>value</b> between the minimum and maximum values.
     * 
     * @param <T> The type of value you want to clamp
     * @param min The smallest possible value
     * @param max The largest possible value
     * @return <b>value</b> if it is within <b>min</b> and <b>max</b>, <b>min</b> if it is below the range, or <b>max</b> if it is above the range
     */
    public static <T extends Comparable<T>> T clamp(T min, T max, T value){
        if (value.compareTo(max) > 0) value = max;
        else if (value.compareTo(min) < 0) value = min;

        return value;
    }
}
