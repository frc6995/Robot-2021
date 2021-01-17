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

    public DrivebaseWheelPercentages setLeftPercentage(double left) {
        this.leftPercentage = left;
        return this;
    }

    public DrivebaseWheelPercentages setRightPercentage(double right) {
        this.rightPercentage = right;
        return this;
    }

    public double getLeftPercentage(){
        return leftPercentage;
    }

    public double getRightPercentage(){
        return rightPercentage;
    }

    public DrivebaseWheelPercentages clamp(){
        this.leftPercentage = MathUtil.clamp(this.leftPercentage, -1, 1);
        this.rightPercentage = MathUtil.clamp(this.rightPercentage, -1, 1);
        return this;
    }

}