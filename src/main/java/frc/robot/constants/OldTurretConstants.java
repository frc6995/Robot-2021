// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import frc.template.Constants;

/** Add your docs here. */
public class OldTurretConstants extends Constants{
    /**
     * The allowed margin of error when checking if the Turret is at its setpoint.
     * The Turret must be withing setpoint - marginOfError < TurretPosition < setpoint + marginOfError
     */
    public static final double marginOfError = 50;
    /**
     * The PID Proportional constant
     */
    public static final double kP = 1;
    /**
     * The PID Integral constant
     */
    public static final double kI = 1;
    /**
     * The PID Derivative constant
     */
    public static final double kD = 1;
    /**
     * The PID Feed Forward constant
     */
    public static final double kFF = 1;
    /**
     * The number of encoder ticks per degree traveled on the Turret
     */
    public static final double encoderTicksPerDegree = 42.0 /*Encoder ticks per rev*/ * 5 * 150 / 18 /*Gear ratio (5:1 and 150:18*/ / 360 /*Degrees per revolution*/;
    /**
     * The encoder count when the Turret is in the homed postion
     */
    public static final double homePosition = 0;
    /**
     * The possible range of motion of the Turret, in degrees
     */
    public static final double softLimit = 270;
    /**
     * The Spark Max's port ID
     */
    public static final int sparkMaxPortID = 1;
    /**
     * The magnetic limit switch's channel ID
     */
    public static final int limitSwitchChannelID = 2;
}
