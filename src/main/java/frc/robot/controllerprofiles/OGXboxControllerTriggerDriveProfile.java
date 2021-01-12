/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllerprofiles;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.utility.inputs.DrivebaseControllerProfile;
import frc.robot.utility.math.NomadMathUtil;

/**
 * An example driver controller profile that shows 
 */
public class OGXboxControllerTriggerDriveProfile extends DrivebaseControllerProfile {
    private final int fwdBackAxis = 1;
    private final int leftRightAxis = 0;
    private final int leftTriggerAxis = 4;
    private final int rightTriggerAxis = 5;
    @Override
    public double getFwdBackAxisValue(GenericHID controller){
        return NomadMathUtil.lerp(controller.getRawAxis(leftTriggerAxis), -1.0, 1.0, 0.0, 1.0) 
             - NomadMathUtil.lerp(controller.getRawAxis(rightTriggerAxis), -1.0, 1.0, 0.0, 1.0);
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
