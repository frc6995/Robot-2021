/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.AutoConstants;
import frc.robot.constants.DriveConstants;
import frc.robot.utility.drivebase.DrivebaseWheelPercentages;
import frc.robot.wrappers.motorcontrollers.NomadBaseMotor;

public abstract class DrivebaseS extends SubsystemBase {
  DifferentialDriveOdometry differentialDriveOdometry = new DifferentialDriveOdometry( new Rotation2d(Math.toRadians(getYaw())));

  /**
   * Creates a new DrivebaseS.
   */
  public DrivebaseS(final DriveConstants driveConstants, final AutoConstants autoConstants) {
    
  }
  /**
   * 
   * @param driveConstants
   * @param leftLeader
   * @param rightLeader
   * @param leftFollower
   * @param rightFollower
   */


  public abstract double getYaw();

  @Override
  public void periodic() {
    // This method will be called once per scheduler run    
  }
  
  public Pose2d getPose(){
    return differentialDriveOdometry.getPoseMeters();
  }

  public abstract DifferentialDriveWheelSpeeds getWheelSpeeds();

  public abstract double getLeftVelocity();

  public abstract double getRightVelocity();
  /**
   * Basic arcade drive. Converts joystick inputs into percent outputs for each side of the drivebase.
   * @param fwdBack The joystick input for the forward/back axis. It is assumed that a value of 1 represents forward, and -1 represents backward.
   * @param leftRight The joystick input for the left/right axis. It is assumed that 1 represents left point turn, and -1 represents right point turn.
   */
  public abstract DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight);

  public abstract void drivePercentages(DrivebaseWheelPercentages percentages);
}
