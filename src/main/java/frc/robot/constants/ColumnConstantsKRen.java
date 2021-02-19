// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import frc.robot.constants.interfaces.ColumnConstants;

/** Add your docs here. */
public class ColumnConstantsKRen implements ColumnConstants {
    @Override
    public int getFrontMotorID() {
        return 41;
    }
    
    @Override
    public int getBackMotorID() {
        return 42;
    }
    
    @Override
    public int getFwdPort() {
        return 4;
    }

    @Override
    public int getRevPort() {
        return 5;
    }

    @Override
    public double getColumnSpeed() {
        return 0.25;
    }

}
