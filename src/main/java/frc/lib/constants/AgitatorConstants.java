// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.constants;

/** Add your docs here. */
public interface AgitatorConstants {
    /**
     * the can id of the right motor
     * @return can id
     */
    int getRightMotorID();

    /**
     * the can id of the left motor
     * @return can id
     */
    int getLeftMotorID();

    /**
     * the speed of the right agitator roller
     * @return speed 
     */
    double getRightSpeed();

    /**
     * the speed of the left agitator roller
     * @return speed
     */
    double getLeftSpeed();
}
