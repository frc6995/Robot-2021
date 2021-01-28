// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/** Add your docs here. */
public class OldTurretConstants{
    /**
     * The allowed margin of error when checking if the Turret is at its setpoint.
     * The Turret must be withing setpoint - marginOfError < TurretPosition < setpoint + marginOfError
     */
    public static final double marginOfError = 50;
    /**
     * The PID Proportional constant
     */
    public static final double K_P = 1;
    /**
     * The PID Integral constant
     */
    public static final double K_I = 1;
    /**
     * The PID Derivative constant
     */
    public static final double K_D = 1;
    /**
     * The PID Feed Forward constant
     */
    public static final double K_FF = 1;
    /**
     * The number of encoder ticks per degree traveled on the Turret
     */
    public static final double ENCODER_TICKS_PER_DEGREE = 42.0 /*Encoder ticks per rev*/ * 5 * 150 / 18 /*Gear ratio (5:1 and 150:18*/ / 360 /*Degrees per revolution*/;
    /**
     * The encoder count when the Turret is in the homed postion
     */
    public static final double HOME_POSITION = 0;
    /**
     * The possible range of motion of the Turret, in degrees
     */
    public static final double SOFT_LIMIT = 270;
    /**
     * The Spark Max's port ID
     */
    public static final int SPARK_MAX_PORT_ID = 1;
    /**
     * The magnetic limit switch's channel ID
     */
    public static final int LIMIT_SWITCH_CHANNEL_ID = 2;
    /**The number of counts that the Turret must be within the margin of error to count as at the setpoint. */
    public static final int MIN_COUNTS_AT_SETPOINT = 10;
}
