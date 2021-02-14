// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/** The Limelight constants for the 2021 robot.
 * 
 * @author JoeyFabel
 */
public class LimelightConstants2021 implements LimelightConstants{

    @Override
    public double getTimeConstant() {
        return 0.1;
    }

    @Override
    public double getTimePeriod() {
        return 0.02;
    }

    @Override
    public double getDistanceToGround() {
        return 10;
    }

    @Override
    public double getDistanceGroundToTarget() {
        return 98.25; // 8' 2.25"
    }

    @Override
    public double getMountingAngle() {
        return Math.toRadians(45);
    }

}
