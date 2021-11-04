// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.utility.drivebase.DrivebaseWheelPercentages;
import frc.robot.subsystems.DrivebaseS;

public class DriveAutoCurvatureC extends CommandBase {
  private DrivebaseS drivebase;
  private boolean forwards;
  private double speed = 0.1;
  private double turnSpeed = 0.0;
  private DrivebaseWheelPercentages percentages;

  /** Creates a new DrivebaseAutoC. */
  public DriveAutoCurvatureC(DrivebaseS drivebase, double timeout, boolean forwards) {
    // Use addRequirements() here to declare subsystem dependencies
    this.drivebase = drivebase;
    this.forwards = forwards;
    addRequirements(drivebase);
  }

  public DriveAutoCurvatureC(DrivebaseS drivebase, double timeout, boolean forwards, double speed, double turnSpeed) {
    this(drivebase, timeout, forwards);
    this.speed = speed;
    this.turnSpeed = turnSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    speed = (speed * (forwards ? 1 : -1));
    //percentages = new DrivebaseWheelPercentages();
    //percentages.setLeftPercentage(speed * (forwards ? 1 : -1));
    //percentages.setRightPercentage(speed * (forwards ? 1 : -1));

    //drivebase.drivePercentages(percentages);
    drivebase.curvatureDrive(speed, turnSpeed, false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivebase.curvatureDrive(speed, turnSpeed, false);
    //drivebase.drivePercentages(percentages);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivebase.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
