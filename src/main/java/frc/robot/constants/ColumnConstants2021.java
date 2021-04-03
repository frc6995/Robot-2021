// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import edu.wpi.first.wpilibj.system.plant.DCMotor;
import frc.robot.constants.interfaces.ColumnConstants;

/** Add your docs here. */
public class ColumnConstants2021 implements ColumnConstants {
    @Override
    public int getTalonID() {
        return 43;
    }
    
    @Override
    public int getAcceleratorID() {
        return 41;
    }
    
    @Override
    public int getFwdPort() {
        return 5;
    }

    @Override
    public int getRevPort() {
        return 4;
    }

    @Override
    public double getColumnLoadSpeed() {
        return 0.25;
    }

    @Override
    public double getColumnFeedSpeed() {
        // TODO Auto-generated method stub
        return 0.75;
    }

    @Override
    public double getAcceleratorRPM() {
        return 5000; //free speed for BAG is 13000 RPM
    }

    @Override
    public double getAcceleratorCurrentLimit() {
        return 5;
    }

    @Override
    public double[] getFeedForwardConstants() {
        double[] constants = {0.0, 0.0, 0.0};
        return constants;
    }

    @Override
    public double getColumnSpeed() {
        // TODO Auto-generated method stub
        return 0.5;
    }

}
