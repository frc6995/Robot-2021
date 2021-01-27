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
     * @param min The lower bound of the output range.
     * @param max The upper bound of the output range.
     * @param percent The percentage, where 0 is the lower bound and 1 is the upper bound.
     * @return The interpolated value.
     */
    public static double lerp(double min, double max, double percent) {
        return min + (percent * (max-min));
      }
}
