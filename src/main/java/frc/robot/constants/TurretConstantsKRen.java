// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import frc.lib.constants.TurretConstants;

/** {@link TurretConstants} that have been set specifically for KRen*/
public class TurretConstantsKRen implements TurretConstants {

    @Override
    public double marginOfError() {
        return 50;
    }

    @Override
    public double kP() {
        return 1;
    }

    @Override
    public double kI() {
        return 1;
    }

    @Override
    public double kD() {
        return 1;
    }

    @Override
    public double kFF() {
        return 1;
    }

    @Override
    public double encoderTicksPerDegree() {
        return 42.0 /*Encoder ticks per rev*/ * 5 * 150 / 18 /*Gear ratio (5:1 and 150:18*/ / 360 /*Degrees per revolution*/;
    }

    @Override
    public double homePosition() {
        return 0;
    }

    @Override
    public double softLimit() {
        return 270;
    }

    @Override
    public int sparkMaxPortID() {
        return 1;
    }

    @Override
    public int limitSwitchChannelID() {
        return 2;
    }

}
