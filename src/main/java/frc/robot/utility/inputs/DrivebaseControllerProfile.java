/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utility.inputs;

import edu.wpi.first.wpilibj.GenericHID;

/**
 * Add your docs here.
 */
public abstract class DrivebaseControllerProfile {
    public abstract int getFwdBackAxisID();
    public abstract int getLeftRightAxisID();

    //Custom behaviors.
    public double getFwdBackAxisValue(GenericHID controller){
        return controller.getRawAxis(getFwdBackAxisID());
    }
    public double getLeftRightAxisValue(GenericHID controller){
        return controller.getRawAxis(getLeftRightAxisID());
    }
}
