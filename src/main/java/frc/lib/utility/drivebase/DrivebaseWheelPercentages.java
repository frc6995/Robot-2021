/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.utility.drivebase;

import edu.wpi.first.wpiutil.math.MathUtil;

/**
 * A simple data carrier to hold a left side wheel percentage and a right side wheel percentage.
 */
public class DrivebaseWheelPercentages {
    double leftPercentage;
    double rightPercentage;

    /**
     * Set the left side wheel percentage to the given value.
     * @param left The percentage
     * @return This {@link #DrivebaseWheelPercentages()}
     */
    public DrivebaseWheelPercentages setLeftPercentage(double left) {
        leftPercentage = left;
        return this;
    }

    /**
     * Set the right side wheel percentage to the given value.
     * @param right The percentage
     * @return This {@link #DrivebaseWheelPercentages()}
     */
    public DrivebaseWheelPercentages setRightPercentage(double right) {
        rightPercentage = right;
        return this;
    }

    /**
     * Get the left side wheel percentage.
     * @return the left wheel percentage
     */
    public double getLeftPercentage(){
        return leftPercentage;
    }

    /**
     * Get the right side wheel percentage.
     * @return the right wheel percentage
     */
    public double getRightPercentage(){
        return rightPercentage;
    }

    /**
     * Clamp both sides' wheel percentages between -100% and 100%.
     * @return this {@link #DrivebaseWheelPercentages()}
     */
    public DrivebaseWheelPercentages clamp(){
        leftPercentage = MathUtil.clamp(leftPercentage, -1, 1);
        rightPercentage = MathUtil.clamp(rightPercentage, -1, 1);
        return this;
    }

}