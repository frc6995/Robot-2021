/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utility.math;

/**
 * Add your docs here.
 */
public final class NomadMathUtil {
    public static double lerp(double value, double min1, double max1, double min2, double max2){
        double percent = (double)(value-min1) / (double)(max1 - min1); //calculates where in the input range the value is.
        double output = min2 + (percent* (max2-min2));
        return output;
    }
        
    public static double lerp(double min, double max, double value) {
        return (1 - value) * min + value * max;
      }
}
