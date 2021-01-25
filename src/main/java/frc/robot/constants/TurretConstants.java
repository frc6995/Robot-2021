// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import frc.template.Constants;

/** Add your docs here. */
public class TurretConstants extends Constants{
    public static final double marginOfError = 50;
    public static final double kP = 1;
    public static final double kI = 1;
    public static final double kD = 1;
    public static final double kFF = 1;
    public static final double encoderTicksPerDegree = 8192 /*Ticks per revolution*/ * 1 /*Revolutions per degree - fix this*/;
}
