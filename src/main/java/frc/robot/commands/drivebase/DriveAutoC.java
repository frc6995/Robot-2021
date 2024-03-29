// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.utility.drivebase.DrivebaseWheelPercentages;
import frc.robot.subsystems.DrivebaseS;

public class DriveAutoC extends CommandBase {
  private DrivebaseS drivebase;
  private boolean forwards;
  private double speed = 0.1;
  private DrivebaseWheelPercentages percentages;

  /** Creates a new DrivebaseAutoC. */
  public DriveAutoC(DrivebaseS drivebase, double timeout, boolean forwards) {
    // Use addRequirements() here to declare subsystem dependencies
    this.drivebase = drivebase;
    this.forwards = forwards;
    addRequirements(drivebase);
  }

  public DriveAutoC(DrivebaseS drivebase, double timeout, boolean forwards, double speed) {
    this(drivebase, timeout, forwards);
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    percentages = new DrivebaseWheelPercentages();
    percentages.setLeftPercentage(speed * (forwards ? 1 : -1));
    percentages.setRightPercentage(speed * (forwards ? 1 : -1));

    drivebase.drivePercentages(percentages);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivebase.drivePercentages(percentages);
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
