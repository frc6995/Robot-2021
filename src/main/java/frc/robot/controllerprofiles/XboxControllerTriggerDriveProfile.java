/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllerprofiles;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.utility.inputs.DrivebaseControllerProfile;

/**
 * An example driver controller profile that shows 
 */
public class XboxControllerTriggerDriveProfile extends DrivebaseControllerProfile {
    private int fwdBackAxis = 1; //TODO update with actual mappings
    private int leftRightAxis = 0;
    private int leftTriggerAxis = 4;
    private int rightTriggerAxis = 5;
    @Override
    public double getFwdBackAxisValue(GenericHID controller){
        return controller.getRawAxis(leftTriggerAxis) - controller.getRawAxis(rightTriggerAxis);
    }

    @Override
    public int getFwdBackAxisID() {
        return fwdBackAxis;
    }

    @Override
    public int getLeftRightAxisID() {
        return leftRightAxis;
    }
}
