// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import frc.robot.constants.interfaces.ColumnConstants;

/** Add your docs here. */
public class ColumnConstants2021 implements ColumnConstants {
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
        return 4;
    }

    @Override
    public int getRevPort() {
        return 5;
    }

    @Override
    public double getColumnLoadSpeed() {
        return 0.25;
    }

    @Override
    public double getColumnFeedSpeed() {
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
    public double getAccelaratorP() {
        return 0;
    }

    @Override
    public double getAcceleratorI() {
        return 0;
    }

    @Override
    public double getAcceleratorD() {
        return 0;
    }

    @Override
    public double getAcceleratorFF() {
        return 0;
    }

    @Override
    public SimpleMotorFeedforward getAcceleratorArbitraryFF() {        
        return new SimpleMotorFeedforward(0.846, 0.0478, 0.000244);
    }

}
