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
    /**
     * The percentage as a decimal for the left side of the drivebase.
     */
    private double leftPercentage;
    /**
     * The percentage as a decimal for the right side of the drivebase.
     */
    private double rightPercentage;

    /**
     * Sets the left side wheel percentage.
     * @param left The new value.
     * @return This DrivebaseWheelPercentages object, modified with the new percentage.
     */
    public DrivebaseWheelPercentages setLeftPercentage(double left) {
        this.leftPercentage = left;
        return this;
    }
    /**
     * Sets the right side wheel percentage.
     * @param right The new value.
     * @return This DrivebaseWheelPercentages object, modified with the new percentage.
     */
    public DrivebaseWheelPercentages setRightPercentage(double right) {
        this.rightPercentage = right;
        return this;
    }
    /**
     * Return the left side percentage.
     * @return
     */
    public double getLeftPercentage(){
        return leftPercentage;
    }
    /**
     * Return the right side percentage.
     * @return
     */
    public double getRightPercentage(){
        return rightPercentage;
    }
    /**
     * Clamps this object's percentages to a [-1, 1] range.
     * @return The object with percentages clamped 
     */
    public DrivebaseWheelPercentages clamp(){
        this.leftPercentage = MathUtil.clamp(this.leftPercentage, -1, 1);
        this.rightPercentage = MathUtil.clamp(this.rightPercentage, -1, 1);
        return this;
    }

}