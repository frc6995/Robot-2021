/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.subsystems;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.utility.drivebase.DrivebaseWheelPercentages;
import frc.lib.wrappers.motorcontrollers.NomadBaseMotor;
/**
 * An abstract Differential Drive drivetrain. This aims to allow trajectory following to mainly stay apart from any specific hardware requirements.
 * This drivetrain style does assume the following hardware:
 * At least one motor controller of a type supported by this library (NomadBaseMotor) on each side. Further followers can be configured in subclasses.
 * A gyro sensor. Only yaw (side to side rotation) will be necessary.
 * An encoder on each side. 
 * 
 */
public abstract class DifferentialDrivebaseS extends SubsystemBase {
  DifferentialDriveOdometry differentialDriveOdometry;
  public DriveConstants driveConstants;
  public AutoConstants autoConstants;
  public NomadBaseMotor leftLeader;
  public NomadBaseMotor rightLeader;

  /**
   * Creates a new DrivebaseS.
   */
  public DifferentialDrivebaseS(final DriveConstants driveConstants, final AutoConstants autoConstants) {
    this.driveConstants = driveConstants;
    this.autoConstants = autoConstants;

    //differentialDriveOdometry = new DifferentialDriveOdometry( new Rotation2d(Math.toRadians(getYaw())));
  }

  public abstract void tankDriveVolts(double left, double right);



  public abstract double getYaw();

  @Override
  public void periodic() {
    updateTelemetry();   
  }
  
  public Pose2d getPose(){
    return differentialDriveOdometry.getPoseMeters();
  }
  
  public abstract DifferentialDriveWheelSpeeds getWheelSpeeds();

  /**
   * Gets the left side velocity in meters per second.
   * @return the left side velocity in meters per second.
   */
  public abstract double getLeftVelocity();
  /**
   * Gets the right side velocity in meters per second.
   * @return the right side velocity in meters per second.
   * @return
   */
  public abstract double getRightVelocity();
  /**
   * Basic arcade drive. Converts joystick inputs into percent outputs for each side of the drivebase.
   * @param fwdBack The joystick input for the forward/back axis. It is assumed that a value of 1 represents forward, and -1 represents backward.
   * @param leftRight The joystick input for the left/right axis. It is assumed that 1 represents left point turn, and -1 represents right point turn.
   */
  public abstract DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight);

  public abstract void drivePercentages(DrivebaseWheelPercentages percentages);

  public abstract void updateTelemetry();

  public abstract void stopMotor();

  public abstract double getLeftSetSpeed();

  public abstract double getRightSetSpeed();

  
}
