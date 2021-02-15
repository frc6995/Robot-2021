// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/** Add your docs here. */
public class ColumnConstantsKRen implements ColumnConstants {
    @Override
    public int getTalonID() {
        return 41;
    }
    
    @Override
    public int getAcceleratorID() {
        return 43;
    }
    
    @Override
    public int getFwdPort() {
        return 5;
    }

    @Override
    public int getRevPort() {
        return 6;
    }

    @Override
    public double getColumnSpeed() {
        return 0.8;
    }

}
