/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.wrappers.motorcontrollers;

/**
 * A stubbed-out NomadBaseMotor for use as a "dummy" leader.
 */
public class NomadNoneMotor implements NomadBaseMotor{

    public static NomadNoneMotor noneMotor = new NomadNoneMotor();

    @Override
    public boolean isLazy() {
        
        return false;
    }

    @Override
    public void setLazy(boolean isLazy) {
        

    }

    @Override
    public NomadBaseMotor setLeader(NomadBaseMotor leader) {
        return this;

    }

    @Override
    public void setInverted(boolean inverted) {
        

    }

    @Override
    public void set(double speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public double get() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean getInverted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stopMotor() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pidWrite(double output) {
        // TODO Auto-generated method stub

    }

    @Override
    public double getActualOutputPercent() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void updateFollower() {
        // TODO Auto-generated method stub

    }
}
