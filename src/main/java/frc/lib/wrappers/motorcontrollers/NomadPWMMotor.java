// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.wrappers.motorcontrollers;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Spark;

/** Add your docs here. */
public class NomadPWMMotor extends Spark implements NomadBaseMotor {
    public NomadPWMMotor(int channel) {
        super(channel);
        // TODO Auto-generated constructor stub
    }


    @Override
    public boolean isLazy() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setLazy(boolean isLazy) {
        // TODO Auto-generated method stub

    }

    @Override
    public NomadBaseMotor setLeader(NomadBaseMotor leader) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return this;
    } 

    @Override
    public double getActualOutputPercent() {
        // TODO Auto-generated method stub
        return get();
    }

    @Override
    public void setOutputVoltage(double voltage) {
        // TODO Auto-generated method stub
        set(voltage/RobotController.getBatteryVoltage());
    }
}
