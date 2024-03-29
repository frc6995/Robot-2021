// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.auto;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.subsystems.DifferentialDrivebaseS;

/** A class to generate a RamseteCommand for path following from given parameters. */
public class NomadAutoCommandGenerator {
    /**
     * Generate a RamseteCommand. Make sure the pose of the drivebase is reset before running the command.
     * @param trajectory The Trajectory the robot should follow.
     * @param drivebaseS The drivebase subsystem.
     * @param driveConstants The selected drive constants.
     * @param autoConstants The selected auto constants.
     * @return ramseteCommand - A RamseteCommand to follow the trajectory. 
     */
    public static RamseteCommand createRamseteCommand(
        Trajectory trajectory,
        DifferentialDrivebaseS drivebaseS,
        DriveConstants driveConstants,
        AutoConstants autoConstants) {
        
            
            
        RamseteCommand ramseteCommand =
        new RamseteCommand(
            trajectory,
            drivebaseS::getPose,
            drivebaseS.getRamseteController(),
            autoConstants.getTrajectoryFeedForward(),
            driveConstants.getDifferentialDriveKinematics(),
            drivebaseS::getWheelSpeeds,
            drivebaseS.getLeftPidController(),
            drivebaseS.getRightPidController(),
            // RamseteCommand passes volts to the callback
            drivebaseS::tankDriveVolts,
            drivebaseS);
        return ramseteCommand;
    }

}
