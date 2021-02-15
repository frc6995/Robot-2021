// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/** The {@link LimelightS} constants.
 * @author JoeyFabel
 */
public interface LimelightConstants {
    /**Get the time constant for the Linear Filter */
    double getTimeConstant();
    /**Get the time period for the Linear Filter */
    double getTimePeriod();
    /**Get the distance between the limelight and the ground */
    double getLimelightHeight();
    /**Get the distance between the ground and the target, in inches */
    double getTargetHeight();
    /**Get the mounting angle of the limelight, in radians */
    double getMountingAngle();
}
