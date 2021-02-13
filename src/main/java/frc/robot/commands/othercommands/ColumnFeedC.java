// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.othercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColumnS;

public class ColumnFeedC extends CommandBase {
  private ColumnS column;

  /** Creates a new ColumnFeedC. */
  public ColumnFeedC(ColumnS columnS) {
    this.column = columnS;
    addRequirements(column);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    column.disableStopper();
    column.setFrontSpeed(column.getConstants().getColumnSpeed());
    column.setBackSpeed(column.getConstants().getColumnSpeed());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    column.setFrontSpeed(0);
    column.setBackSpeed(0);
    column.enableStopper();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
