// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import frc.robot.constants.interfaces.ShooterConstants;

/** The {@link ShooterConstants} for our 2021 robot. */
public class ShooterConstants2021 implements ShooterConstants {
    public int getLeadMotorID(){
        return 3;
    }

    public int getFollowerMotorID(){
        return 4;
    }

    public double getKP() {
        return 0;
    }

    public double getKI() {
        return 0;
    }

    public double getKD() {
        return 0;
    }

    public double getKFF() {
        return 0;
    }

    public double getIZone(){
        return 0;
    }

    public double getAllowableRPMError(){
        return 10;
    }

    public double getAverageVoltage(){
        return 10.0;
    }

    public double getAllowableVoltageError(){
        return 0.25;
    }

    @Override
    public boolean getLeadMotorInverted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getFollowerMotorInverted() {
        // TODO Auto-generated method stub
        return false;
    }
}
